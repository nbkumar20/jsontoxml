# jsontoxml
This is a simple java application that converts json input to xml format

1. Getting Started
The git url to clone the project to local

git clone https://github.com/nbkumar20/jsontoxml.git

2.JSON to XML Speciﬁcation

2.1 Overview

JSON does not strictly map to XML in a single way. There are many diﬀerent ways one could create a mapping from a JSON document to a XML document. This section describes the mapping that your code MUST follow in order to be considered valid.

2.2 General Constraints

In JSON objects you are given name value pairs. This can be mapped to XML in several ways. For this program, the XML element name corresponds to the type of the element, not the name of the element. The name of the JSON element in a JSON object is given as an attribute on the XML element. Only JSON array and object values may be at the top level of a ﬁle. The speciﬁcs of these values are discussed in greater detail in later sections.

2.3 JSON Types

JSON supports the following types, all of which must be mapped into your XML output. Only JSON objects and arrays are supported as top level values.

 ![image](https://user-images.githubusercontent.com/81207782/112091055-aa2a2a00-8bba-11eb-86fa-2c5e7a60e863.png)


2.3.1 Number

A JSON element with a number as a value should map to an XML element named <number>, with the number as the single and only value between the opening and closing tags. An example of this is shown in ﬁgure 2 and 3.



2.3.2 String

A JSON element with a string as a value should map to an XML element named <string>, with the string as the single and only value between the opening and closing tags. An example of this is shown in ﬁgure 4 and 5.

2.3.3 Boolean

A JSON element with a Boolean as a value should map to an XML element named <boolean>, with the boolean as the single and only value between the opening and closing tags. The only valid boolean values are true and false.An example of this is shown in ﬁgure 6 and 7.

 ![image](https://user-images.githubusercontent.com/81207782/112091074-b44c2880-8bba-11eb-8232-8cef88647561.png)
![image](https://user-images.githubusercontent.com/81207782/112091112-c8902580-8bba-11eb-8b6c-da90d7c5f9a0.png)
![image](https://user-images.githubusercontent.com/81207782/112091124-ce860680-8bba-11eb-8761-e07d9824722b.png)
![image](https://user-images.githubusercontent.com/81207782/112091132-d180f700-8bba-11eb-889a-dae52621a083.png)



2.3.4 Array

A JSON element with an array as a value should map to an XML element named <array>. For each element in the JSON array there should be a corresponding XML sub-element denoting the value and type. A JSON array may contain arbitrary sub values. type. Elements of the array should not have a name attribute. Remember, JSON arrays are not required to be homogeneous! An example of this is shown in ﬁgure 8 and 9.

2.3.5 Object

A JSON element with an object as a value should map to an XML element named <object>. Objects can contain arbitrary sub-values.An example of this is shown in ﬁgure 10. Objects are the only JSON types that generate name attributes on XML tags.

 ![image](https://user-images.githubusercontent.com/81207782/112091146-d6de4180-8bba-11eb-98c4-1ce32df0ed0c.png)
![image](https://user-images.githubusercontent.com/81207782/112091156-dc3b8c00-8bba-11eb-986b-d0a25c17ac00.png)
![image](https://user-images.githubusercontent.com/81207782/112091164-df367c80-8bba-11eb-8b89-8bc4aa54354e.png)

2.3.6 null

A JSON element with an null as a value should map to an XML element named <null/>. Unlike other XML representations for JSON values, a null value does not have a closing tag, rather it is self-closing by adding a forward slash before the end of the element tag. An example of this is shown in ﬁgure 11 and 12.


Sample json and xml files are present in src/main/resources folder
