package tjf.simpleCalculator.impl;

import tjf.simpleCalculator.ISimpleCalculator;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * TODO: 目前还不支持显式录入负数
 * @ClassName: SimpleCalculatorImpl
 * @Description:
 * @Author: yinhutjfox
 * @Date: 2019/4/3 16:44
 */
public class SimpleCalculatorImpl implements ISimpleCalculator {

    private final static Pattern numberPat = Pattern.compile("^(((0|[1-9][0-9]*)\\.[0-9]*)|((0|[1-9][0-9]*)))$");

    private final static Pattern symbolPat = Pattern.compile("^[\\\\*\\-+^)(]$");

    @Override
    public double calculate(String exp) {
        StringBuffer expBuf = new StringBuffer(exp.replaceAll(" ", ""));
        Stack<Character> symbolStack = new Stack<>();
        Stack<Double> result = new Stack<>();
        for (int i = 0; i < expBuf.length(); ++i) {
            if (isSymbol(expBuf.charAt(i))) {
                char symbol = expBuf.charAt(i);
                if (0 != i) {
                    double num = parseContent(getBefor(expBuf, i));
                    result.push(num);
                }
                if (')' == symbol) {
                    char checkSymbol;
                    while ('(' != (checkSymbol = symbolStack.pop())) {
                        calculate(result, checkSub(symbolStack , checkSymbol));
                    }
                } else {
                    char preSymbol;
                    while (true) {
                        if (!symbolStack.empty() && '(' != symbol) {
                            preSymbol = symbolStack.peek();
                            if ('(' != preSymbol) {
                                if (0 < compareSymbolLevel(preSymbol, symbol)) {
                                    char oper = symbolStack.peek();
                                    while ('(' != oper && 0 == compareSymbolLevel(oper, preSymbol)) {
                                        symbolStack.pop();
                                        calculate(result, oper);
                                        if (symbolStack.empty()) {
                                            break;
                                        }
                                        oper = symbolStack.peek();
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    symbolStack.push(symbol);
                }
                removeBefore(expBuf, i);
                i = -1;
            }
        }
        if (0 != expBuf.length()) {
            result.push(parseContent(expBuf.toString()));
        }
        while (!symbolStack.empty()) {
            char checkSymbol = symbolStack.pop();
            calculate(result, checkSub(symbolStack , checkSymbol));
        }
        return result.peek();
    }

    private char checkSub(Stack<Character> symbolStack, char checkSymbol) {
        if (!symbolStack.empty()) {
            char preSymbol = symbolStack.peek();
            if ('-' == preSymbol) {
                switch (checkSymbol) {
                    case '+':
                        checkSymbol = '-';
                        break;
                    case '-':
                        checkSymbol = '+';
                        break;
                    default:break;
                }
            }
        }
        return checkSymbol;
    }

    private int compareSymbolLevel(char lsh, char rsh) {
        return getSymbolLevel(lsh) - getSymbolLevel(rsh);
    }

    private int getSymbolLevel(char symbol) {
        int level = 0;
        if ("*\\".contains(symbol + "")) {
            level = 1;
        } else if ('^' == symbol) {
            level = 2;
        }
        return level;
    }

    private void calculate(Stack<Double> result, char symbol) {
        double a = result.pop();
        double b = result.pop();
        switch (symbol) {
            case '+':
                result.push(b + a);
                break;
            case '-':
                result.push(b - a);
                break;
            case '*':
                result.push(b * a);
                break;
            case '\\':
                result.push(b / a);
                break;
            case '^':
                result.push(Math.pow(b, a));
                break;
            default:
                break;
        }
    }

    private double parseContent(String content) {
        if (numberPat.matcher(content).find()) {
            return Double.parseDouble(content);
        }
        throw new RuntimeException("数字格式不对");
    }

    private boolean isSymbol(char content) {
        return symbolPat.matcher(content + "").find();
    }

    private String getBefor(StringBuffer stringBuffer, int index) {
        return stringBuffer.substring(0, index);
    }

    private void removeBefore(StringBuffer stringBuffer, int index) {
        stringBuffer.replace(0, index + 1, "");
    }

}
