package lambda.curried;

import java.util.function.Function;

public class Example1 {

    public static void main(String[] args) {
        Function<String, Function<String, String>> curriedSumStrings = curriedReversedSumStrings();
        System.out.println(curriedSumStrings.apply("b").apply("a"));

        Function<String, Function<String, Function<String, String>>> summator = left -> delimiter -> right -> delimiterSum(left, delimiter, right);
        Function<String, Function<String, String>> sumWithDelimiter = partiallyAppliedSumWithDelimiter(summator, "-");
        System.out.println(sumWithDelimiter.apply("a").apply("b"));
    }

    //(String, String) -> String
    private static String sumStrings(String left, String right) {
        return left + right;
    }

    // () -> String -> String -> String
    private static Function<String, Function<String, String>> curriedReversedSumStrings() {
        return new Function<String, Function<String, String>>() {
            @Override
            public Function<String, String> apply(String right) {
                return new Function<String, String>() {
                    @Override
                    public String apply(String left) {
                        return sumStrings(left, right);
                    }
                };
            }
        };
    }

    private static String delimiterSum(String left, String delimiter, String right) {
        return left + delimiter + right;
    }

    // (() -> String -> String -> String -> String) -> String -> String
    private static Function<String, Function<String, String>> partiallyAppliedSumWithDelimiter(
            Function<String, Function<String, Function<String, String>>> summator,
            String delimiter
    ) {
        return left -> right -> summator.apply(left).apply(delimiter).apply(right);
    }
}
