# issue
# 一段字符串，长80个字符为一行（不超过80）
# 字符串中有各种标点符号,.;等
# 分开的每行不能以空格和标点符号开始
# 分开的每行尾不能以分割的单词结尾
# 单词不能从中间断开
strin = """ As you may know, in addition to presenting well-known, classic masterpieces, we also focus on art often forgotten by Academic, Western art history. 
Today we want to show you a forgotten woman artist who worked during the Dutch Golden Age: Maria Schalcken. 
She was the younger sister of the painter Godfried Schalcken. It was often the case that paintings created by women were attributed by scholars to men; 
that was also the case here. 
"""


def main():
    return format_str(strin)


def format_str(x: str):
    """格式化长字符串，返回多行文本，每行不超过80字符"""
    str_list = list()
    bd = [',', '.', ';', ':', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
          's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
    count = len(x) // 80 + 1

    while count:
        strin_new = x.replace('\n', '').strip()
        if len(strin_new) > 80:
            if strin_new[80] not in bd:
                str_goal = strin_new[0:80] + '\n'
                str_list.append(str_goal)
                x = strin_new.replace(strin_new[0:80], '')
            else:
                insdex_goal = strin_new[0:80].rfind(' ')
                str_goal = strin_new[0:insdex_goal].strip(' ') + '\n'
                str_list.append(str_goal)
                x = strin_new.replace(strin_new[0:insdex_goal], '')
        else:
            if len(strin_new) <= 80:
                str_goal = x.strip(' ') + '\n'
                str_list.append(str_goal)
            else:
                print('最后一行数据异常')
        count -= 1
    return ''.join(str_list)


if __name__ == "__main__":
    print(main())
