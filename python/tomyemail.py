import smtplib
from email.message import EmailMessage
import ssl

EMAIL_ADDRESS = "cy1213@163.com"
EMAIL_PASSWORD = "XXXXXXXX"
PROVINCE = "陕西"
mycontext = ssl.create_default_context()
# 加密连接
subject = "测试"
body = "这是一条测试信息"
msg = EmailMessage()
msg['subject'] = subject
msg['from'] = EMAIL_ADDRESS
msg['to'] = EMAIL_ADDRESS
msg.set_content(body)
msg.add_alternative("""\
<html lang="zh-CN">
	<head>
	    <meta charset="utf-8"/>
		<title>表格文档</title>
		<meta name="author" content="ChenYu"/>
		<meta name="description" content="Test Web Docs."/>
		<!-- <link href="minimal-table.css" rel="stylesheet" type="text/css"> -->
	    <style>
		html {
		  font-family: sans-serif;
		}

        table {
          border-collapse: collapse;
          border: 2px solid rgb(200,200,200);
          letter-spacing: 1px;
          font-size: 0.8rem;
		  border-collapse: separate;
        }
        
        td, th {
          border: 1px solid rgb(190,190,190);
          padding: 10px 20px;
        }
        
        th {
          background-color: rgb(235,235,235);
        }
        
        td {
          text-align: center;
        }
        
        tr:nth-child(even) td {
          background-color: rgb(250,250,250);
        }
        
        tr:nth-child(odd) td {
          background-color: rgb(245,245,245);
        }
        
        caption {
          padding: 10px;
        }
	    </style>
	</head>
	<body>
		<table>
			<tr>
			    <td>&nbsp;</td>
				<th scope="col"  colspan="1">表格一</th>
					<!-- th:table header 指定表头-列 -->
				<th scope="col" colspan="1">表格二</th>
				<th scope="col" colspan="1">表格三</th>
				<th scope="col" colspan="1">表格四</th>
			</tr>
			<tr>
			    <th scope="row" rowspan="1">姓名</th>
					<!-- th:table header 指定表头-行 -->
				<td>张三</td>
				<td>李四</td>
				<!-- <td style="background-color: yellow">李四</td> -->
					<!-- style 指定背景颜色 -->
				<td>王五</td>
				<td>赵六</td>
			</tr>
			<tr>
			    <th scope="row" rowspan="1">年龄</th>
				<td>25</td>
				<td>26</td>
				<td>27</td>
				<td>28</td>
			</tr>
			<tr>
			    <th scope="row" rowspan="1">省份</th>
				<!-- <td> -->
					<!-- 单元格嵌套 -->
					<!-- <table id="table2"> -->
						<!-- <tr> -->
							<!-- <td> 河南</td> -->
						<!-- </tr> -->
					<!-- </table> -->
				<!-- </td> -->
				<td>河南</td>
				<td>山东</td>
				<td>安徽</td>
				<td>%s</td>
			</tr>
		</table>
	<body>
</html>
""" % (PROVINCE), subtype='html')

with smtplib.SMTP_SSL("smtp.163.com", 465, context=mycontext) as smtp:
    print("加密连接")
    # 登录邮箱, 返回认证成功
    smtp.login(EMAIL_ADDRESS, EMAIL_PASSWORD)
    print("登录")
    # 发送信息
    send = smtp.send_message(msg)
    print("发送完毕")
