# Types   


```
val x: Int = 1
val s: String = "a string"
val p: Person = new Person("Regina")
```  


## If     

```
if (test1) {
    doA()
} else if (test2) {
    doB()
} else if (test3) {
    doC()
} else {
    doD()
}

However, unlike Java and many other languages, the if/else construct returns a value, so, among other things, you can use it as a ternary operator:

val x = if (a < b) a else b

```
