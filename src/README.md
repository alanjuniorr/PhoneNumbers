# Java Application

## Exercise

This is an assessment project, the objective is to process a list of phone numbers taking into account several factors, including the country code.

Documentation is not mandatory, it's up to you to decide if you should include it.

Given a list of country codes (provided in attachment `coutryCodes.txt`) which includes a country code in each line, and an input file (`input.txt`) which includes a phone number in each line process the phone numbers according to the following rules:

A phone number is only valid if:

- It has 4 to 6 digits (inclusive) and is considered a `Short Number`
- It has 9 to 14 digits (inclusive) and is considered a `Long Number`
- A `Short Number` is a shortcut to a portuguese long number. A `Long Number` is a complete phone number that must include a country code (351, 44, etc)
- A `Short Number` cannot start with `0`
- A `Long Number` can start with or without a `+` character and the `+` character doesn't count for the 9 to 14 digits limit
- Alternatively a `Long Number` can start with a `00` if it doesn't start with a `+`. '00' also doesn't count for the 9 to 14 digits limit
- Numbers cannot have any letters or special characters like `/*-)(/#$` (or any other visible character), except for the `+` at the beginning of the number
- For a `Long Number`, no type of white space is allowed between the `+` and country code or between the `00` and country code, but any other whitespace is valid and it doesn't count as a character for the number limit
- A `Short Number` cannot have any type of white spaces
- A `Long Number` must have a valid country code, otherwise it's invalid

## Run

We expect a small Java console application to be executed similar to this:

```bash
java -jar "your_jar_name" input.txt
```

Your jar should automatically import the provided `countryCodes.txt` file placed next to the jar file.

## Output

For the output we expect a list of country names and the amount of numbers for that country ordered by descending amount of numbers. Short numbers count as portuguese numbers. Example:

```text
United Kingdom:61
Spain:43
Vietnam:34
Portugal:27
... and so on
```

In this example the United Kingdom had 61 numbers on the input file, Spain had 43 and so on.

Some of the lines present in `countryCodes.txt` file:

```text
Afghanistan-93
Albania-355
Algeria-213
Andorra-376
Angola-244
Antarctica-672
```

## `Short Number` examples

Examples of valid `Short Number`:

```text
9874
65468
88876
446670
```

Examples of invalid `Short Number`:

```text
446
065 4
7443213
```

## `Long Number` examples

Examples of valid `Long Number`:

```text
+44 65465444
+351 918 878 443
00198798798
```

Examples of invalid `Long Number`:

```text
+ 4465465444
987
42298798798
351987987987987321
```
