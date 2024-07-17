"""
------------------------------
File Name: cytoemail.py
Description: 用于发送email信息
Python Version: 3.7.9

Author: ChenYu
Change Activity:
    20240717
------------------------------
"""
import ssl
import smtplib
from email.message import EmailMessage
import cytools

EMAIL_ADDRESS = "XXXXXX@163.com"
EMAIL_PASSWORD = "XXXXXX"
SUBJECT = "信息"
LIST_DICT = [{'NAME': '百货认沽'}, {'NAME': '上权利'}, {'NAME': '吉炭GP1'}, {'NAME': '冶钢YP1'},
             {'INFO': '赤湾SP1'}, {'INFO': '产品认沽'}, {'INFO': '钾肥FP1'}, {'INFO': '盐湖HP1'}, {'INFO': '集琦QP1'}]
LIST_TUPLE = [('百货认沽', '华百货认沽'), ('下权利', '上权利'), ('权力TG1', '吉炭GP1'), ('炼铝YP1', '冶钢YP1'), ('亚湾SP1', '赤湾SP1'),
              ('产品认沽', '农品认沽'), ('氮肥', '钾肥FP1'), ('水壶', '盐湖HP1'), ('海底er捞', '集琦QP1')]
html = cytools.output_html


def to_email(add, passwd, sub, d):
    """
    发送邮件
    :param add: str
    :param passwd: str
    :param sub: str
    :param d: list
    :return: None
    """
    my_context = ssl.create_default_context()
    msg = EmailMessage()
    msg['subject'] = sub
    msg['from'] = add
    msg['to'] = add
    msg.add_alternative(html(d), subtype='html')
    with smtplib.SMTP_SSL("smtp.163.com", 465, context=my_context) as smtp:
        print("连接")
        smtp.login(add, passwd)
        print("登录")
        smtp.send_message(msg)
        print("发送完毕")


if __name__ == '__main__':
    to_email(EMAIL_ADDRESS, EMAIL_PASSWORD, SUBJECT, LIST_DICT)
    # to_email(EMAIL_ADDRESS, EMAIL_PASSWORD, SUBJECT, LIST_TUPLE)
