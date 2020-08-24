package homework.Task25;

import java.util.ArrayDeque;

public class Task25Implementation implements Task25 {

    @Override
    public boolean isNormalBrackets(String string) {
        boolean checkedResult = true;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        loop:
        for (int i = 0; i < string.length(); i++) {
            char temp = string.charAt(i);
            switch (temp) {
                case '(' :
                    stack.push(temp);
                    break;
                case ')' :
                    if (stack.poll() == null) {
                        checkedResult = false;
                        break loop;
                    }
                    break;
            }
        }
        return checkedResult && stack.poll() == null;
    }
}
