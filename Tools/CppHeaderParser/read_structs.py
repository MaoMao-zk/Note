#!/usr/bin/python
import json
import sys
sys.path = ["../"] + sys.path
import CppHeaderParser
try:
    cppHeader = CppHeaderParser.CppHeader("dl_rlc_ul_rlc_interface.h")
except CppHeaderParser.CppParseError as e:
    print(e)
    sys.exit(1)

# print("CppHeaderParser view of %s"%cppHeader)

output_json = {}

# MARCO
marcos = {}
for define in cppHeader.defines:
    pair = define.split(" ")
    key = pair[0]
    i=1
    while(pair[i] == "") :
        i = i +1
    value = pair[i]
    # print(key + " = " + value)
    marcos[key] = value
output_json["macros"] = marcos

# Struct
structs = []
for object in cppHeader.classes:
    struct = cppHeader.classes[object.__str__()]
    struct_json = {}
    struct_json["name"] = struct["name"]
    # print("name is " + struct["name"])
    struct_json["props"] = []
    properties = struct["properties"]["public"]
    for prop in properties:
        # print("prop is " + prop["type"] + " " + prop["name"])
        # print("prop is " + prop.__str__())
        prop_json = {"type":prop["type"], "name":prop["name"]}
        struct_json["props"].append(prop_json)
    # print("struct json:\n" + json.dumps(struct_json, indent=4))
    structs.append(struct_json)
output_json["structs"] = structs

print(json.dumps(output_json, indent=4))

