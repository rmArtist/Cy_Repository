# -*- coding:utf-8 -*-
import json
import requests

URL = "http://host:8080/v1/node"
JSONDATA = '[{"age": "16.29h","lastRequestTime": "2024-06-18T01:16:41.514Z","lastResponseTime": "2024-06-18T01:16:41.516Z","recentFailureRatio": 0.0,"recentFailures": 0.0,"recentFailuresByType": {},"recentRequests": 120.00277776491774,"recentSuccesses": 120.00277776491774,"uri": "http://10.84.196.97:8080"}]'
inputKeyList = ['age', 'lastRequestTime']
keyList = []
keyValueList = []
outputValueList = []


class Request(object):
    def get_all_metrics(self, url):
        result = requests.get(url).text.split('\n')
        return result


class GetTarget(object):
    # 获取json
    def GetJson(self, data):
        # jsonData = Request.get_all_metrics(URL)
        text = json.loads(data)
        return text

        # text = json.loads(jsonData)

    # 读取所有的key
    def GetAllKey(self, jsondata):
        for i in jsondata:
            for key in i.keys():
                if key:
                    keyList.append(key)
            return keyList

    # 获取某个key对应的value
    def GetOneValue(self, jsondata, onekey):
        for element in jsondata:
            value = element.get(onekey)
            return value

    # 读取所有的元素
    def GetAllKeyValue(self, jsondata):
        for i in jsondata:
            for element in i.items():
                kv_str = str(element).replace(",", ":").replace("'", "").replace("(", "").replace(")", "")
                keyValueList.append(kv_str.splitlines())
            return keyValueList

    # 读取特定元素
    def GetAnyoneValue(self, jsondata, inputlist):
        for key in inputlist:
            value = self.GetOneValue(jsondata, key)
            outputValueList.append(value)
        return outputValueList


# 主程序
if __name__ == '__main__':
    gt = GetTarget()
    # 获取数据
    jsonlist = gt.GetJson(JSONDATA)
    print('通过url获取的json数据: ', jsonlist)
    # 获取所有key
    all_key_list = gt.GetAllKey(jsonlist)
    print('获取json数据中所有的key: ', all_key_list)
    # 获取某个key对用的value
    one_value = gt.GetOneValue(jsonlist, 'age')
    print('age对应的值为: ', one_value)
    # 获取所有的key和value
    all_key_value = gt.GetAllKeyValue(jsonlist)
    print('json数据中所有的key和value: ', all_key_value)
    # 读取特定元素
    anyone_value = gt.GetAnyoneValue(jsonlist, inputKeyList)
    print('获取指定的任意key的value: ', anyone_value)
