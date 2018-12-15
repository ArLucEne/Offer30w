### 题目：Design a HashSet without using any built-in hash table libraries. ###
#### To be specific, your design should include these functionsadd(value): Insert a value into the HashSet. contains(value) : Return whether the value exists in the HashSet or not.remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing. ####、

> HashSet底层由HashMap实现，即所有值为hashmap的key，所有key指向 static final PRESENT

> Set集合包括HashSet和TreeSet，后者底层实现为二叉树 

- 一定熟悉起码的集合工具API，如ArrayList
- [https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html "ArrayList文档")

