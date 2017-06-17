This is a porting to Kotlin of the original kata by [@xpmatteo](https://github.com/xpmatteo).
You can find the original readme at [here](originalRDME.md).

The **goal** of this version is to do a complete migration from `Java` to `Kotlin`.
The kata itself is not really complex so what I'm going to try is to refactor it, add several spring-boot features and then migrate it completely.

Usually this is a common use case in companies where you start with microservices in Java.

Welcome to `kata` branch!

####Iteration \#1
I used this [link] to migrate from a `java` project to a mixed project `kotlin`/`java`.

The main point is that `kotlin` code should compile before `java` code so that it can be used.

Test is still failing so everything is being executed

####Iteration \#2
Started refactoring in `Java`. Almost addressed all the peculiar points.

####Iteration \#3
Added spring-boot as dependency and changed the acceptance test to use `@SpringBootTest`

####Iteration \#4
Added spring plugin, continued conversion to kotlin. Removing checked exception declaration. (Kotlin does not have checked :) )

####Iteration \#5
Moved all production code to Kotlin

####Iteration \#6
Really small iteration to write a method in a more idiomatic way:
```
    fun recoverEmployees(): List<Employee> {
        return File(fileName).useLines { lines ->
            lines.drop(1).map { line ->
                val employeeData = line.split(", ")
                Employee(employeeData[1], employeeData[0], XDate(employeeData[2]), employeeData[3])
            }.toList()
        }
    }
```

