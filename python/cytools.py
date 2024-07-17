"""
------------------------------
File Name: cytools.py
Description: 用于处理包含字典或元组元素的列表, 生成格式化效果的html文档
Python Version: 3.7.9

Author: ChenYu
Change Activity:
    20240717
------------------------------
"""

import pandas as pd
from jinja2 import Template

HTML_TEXT = """\
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8"/>
        <title>信息</title>
        <meta name="author" content="ChenYu"/>
        <meta name="description" content="Test Web Docs."/>
        <style>
        html {font-family: sans-serif;}
        table {border-collapse: collapse;border: 2px solid rgb(200,200,200);letter-spacing: 1px;font-size: 0.8rem;border-collapse: separate;}
        td, th {border: 1px solid rgb(190,190,190);padding: 10px 20px;}
        th {background-color: rgb(135, 206, 250);}
        td {text-align: center;}
        tr:nth-child(even) td {background-color: rgb(250,250,250);}
        tr:nth-child(odd) td {background-color: rgb(245,245,245);}
        caption {padding: 10px;}
        .special {color: red;}
        p {color: blue;}
        .outer {border: 5px solid black;}
        .box {padding: 10px;width: calc(90% - 30px);background-color: rebeccapurple;color: white;}
        </style>
    </head>
    <body>{{ table_html }}<body>
</html>
"""


def get_str(s):
    result = ''.join(s)
    return result


def get_list_str(s):
    result = s + ' = []'
    return result


def max_len(s):
    lem = []
    for i in s:
        lem.append(len(eval(i)))
    return max(lem)


def change_dict(d):
    """
    解析包含字典元素的列表返回新的列表
    :param d: list
    :return: list
    """
    a = set(map(get_str, d))
    b = list(map(get_list_str, a))
    for i in b:
        exec(i)
    elem_dict = {}
    lem = []
    for i in a:
        for j in d:
            if i == get_str(j):
                eval(i).append(get_str(j.values()))
        lem.append(len(eval(i)))
    max_le = max(lem)
    for i in a:
        le = len(eval(i))
        if (le == max_le) is False:
            for ti in range(max_le - le):
                eval(i).append('')
        elem_dict[i] = eval(i)
    elem_dict = dict(sorted(elem_dict.items(), key=lambda x: list(x[1]).count('')))
    return elem_dict


def get_html(d):
    """
    解析包含字典或元组元素的列表返回html表格
    :param d: list
    :return: html
    """
    if isinstance(d[0], dict):
        result = change_dict(d)
        tb = pd.DataFrame(result) \
            .style \
            .hide_index() \
            .to_html(buf=None, table_uuid='cy_dict_to_table')
        return tb
    elif isinstance(d[0], tuple):
        tr_list = []
        for i in d:
            td_list = []
            for j in i:
                td_list.append('<td>' + j + '</td>')
            td = get_str(td_list)
            tr = '<tr>' + td + '</tr>'
            tr_list.append(tr)
        tr = get_str(tr_list)
        tb = '<table>' + tr + '</table>'
        return tb
    else:
        tb = '<table><tr><td>输入内容有误</td></tr></table>'
        return tb


def output_html(d):
    """
    解析包含字典元素或元组元素的列表返回完整html
    :param d: list
    :return: html
    """
    table_html = get_html(d)
    template = Template(HTML_TEXT)
    html = template.render(table_html=table_html)
    return html
