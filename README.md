# kunit
Lightweight Assertion Framework for Kotlin Unit-Tests

(under construction ... sadly it takes some time to extract all related stuff from our main product into this project ... I expect to have a working solution at 10.04.18 ... if not so, I don't think it will ever be finished)

## Examples

```kotlin
@Test fun `some example assertions`(){
  val number = 1 + 3
  
  number mustBe 4
  number mustBeHigherThan 3
  number mustBeLowerOrEqualThan 10
  
  val name = "Chris"
  
  name mustBe "Chris"
  name mustStartWith "Chr"
  name mustHaveLength 5
   
}
```

For more examples have a look at the [API documentation](#api)


## Why another testing library?

The goal was and is to have a pretty **easy to use** Assertion Framework for unit tests written in Kotlin (https://kotlinlang.org/).

It should use the **advantages of kotlin** without reinventing the wheel.

All other existing framework (at least at the moment of writing this) have some other focus.

# Details
## Should it be or must it be ... that is the question

Some kind of religious question in my eyes. What do you want to say?

 - `x shouldBe 12`
 
 or is it more like 

- ` x mustBe 12`

Since there will be an `AssertionError` in case condition is `false` I decided to use `mustXXX`

## Other Frameworks

- [Kotlin-Test](https://github.com/kotlintest/kotlintest)
  - A really cool and extensive testing framework
  - If you start from scratch this is possibly to best choice
  - Negatives
    - It is definitely *not* lightweight
    - Writing Test in `init{}` block feels wrong
    - To be forced to extend some basic test class (i.e. `StringSpec`) is not acceptable in languages that don't
      support multiple inheritance
    - It could be simpler to use
- [Spek](https://github.com/spekframework/spek)
  - This Framework focusses on structure of tests but not on assertions
- [Hamkrest](https://github.com/npryce/hamkrest)
  - Is just Hamcrest with a few syntactical sugar
  - `assert.that(input, String::isBlank)` doesn't look like the optimal solution
- [Expekt](https://github.com/winterbe/expekt)
  - probably the closest solution to my expectations
  - I personally like this solution very much
- [Kluent]()https://markusamshove.github.io/Kluent)
  - this is very close to what I want to do
  - _To be honest: If I would have found this library earlier I wouldn't
    have started writing my own one. Both are very similar. Nevertheless I am now
    at a point where I don't want to throw away my solution._

## Highlights
- Uniform and consistent syntax
- Nice failure messages which are easy to read and parseable for IDEs like IntelliJ 

```
>-------------------------------------------------------------
Following values were expected to be equal:
  expected : 6
    actual : 5
------------------------------------------------------------
(for IntelliJ)
expected: 6 but was: 5
```


    
## Api<a name="api"></a>

### General assertions
