package reg;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by xiaohe on 14-11-22.
 */
public class RegHelper {
    public static final int INT_TYPE = 0;
    public static final int FLOAT_TYPE = 1;
    public static final int DOUBLE_TYPE = 2;
    public static final int LONG_TYPE = 3;
    public static final int STRING_TYPE = 4;

    // '_' | '[' | ']' | '/' | ':' | '-' | '.' | '!')* | '"'[^'"']*'"'
    public static final Pattern DoubleQuotesRegEx = Pattern.compile("\"([^\"]+)\"");
    public static final Pattern StringRegEx = Pattern.compile(addSpace("([\\p{Alnum}_\\[\\]\\/\\:\\-\\.\\!]+)")
            + "|" + addSpace("(" + DoubleQuotesRegEx + ")"));

    public static final Pattern FieldRegEx = Pattern.compile(addToGroup(StringRegEx.toString()));
    public static final Pattern FieldsRegEx = Pattern.compile(FieldRegEx + addToGroup("," + FieldRegEx) + "*");

    public static final Pattern TupleRegEx = Pattern.compile(RegHelper.addRealParen(FieldsRegEx.toString()));
    public static final Pattern TupleListRegEx = Pattern.compile(TupleRegEx + "+");

    public static final Pattern EventName = Pattern.compile(addToGroup(StringRegEx.toString()));

    public static final Pattern TimeStamp = Pattern.compile(addToGroup(addSpace("@" + ("\\d+"))));

    public static final Pattern TableRegEx = Pattern.compile(addToGroup(EventName + addToGroup(TupleRegEx + "+")));

    public static final Pattern Delim4FindingTimeStamp = Pattern.compile("[a-zA-Z]" + "|" + TupleListRegEx);
    public static final Pattern Delim4FindingTupleList = Pattern.compile(TableRegEx + "|@");
    public static final Pattern Delim4FindingEvent = Pattern.compile(TupleListRegEx.toString());

    public static final Pattern SkipEvent = Pattern.compile("\\s|" + TupleRegEx);

    public static final String addRealParen(String str) {
        return addToGroup(addSpace("\\(") + str + addSpace("\\)"));
    }

    public static final String addToGroup(String str) {
        return "(" + str + ")";
    }

    public static final String addSpace(String str) {

//        return "\\s*" + str + "\\s*";
        return str;
    }

    public static void main(String[] args) {
//           String input="publish(7)";
//    System.out.println(input.matches(TableRegEx));
//
//    input="@10publish(7)(22)approve(2)good(3)";
//    System.out.println(input.matches(TimeStamp + addToGroup(TableRegEx + "+")));
//
//    Scanner scan=new Scanner(input);
//    System.out.println("Has next timestamp: " + scan.useDelimiter(EventName).hasNext(TimeStamp));
//    System.out.println(scan.next(TimeStamp)+" is the ts");
//    System.out.println("Has next timestamp: "+scan.useDelimiter(EventName).hasNext(TimeStamp));
//    System.out.println("Has next eventName: "+scan.useDelimiter(TupleRegEx).hasNext(EventName));
//    System.out.println("Next event is "+scan.next(EventName));
//
//    Pattern pat= Pattern.compile(TableRegEx);
//    Matcher matcher=pat.matcher(input);
//    int count=0;
//    while (matcher.find()){
//        count++;
//        int gNum=matcher.groupCount();
//        System.out.println("There are "+gNum+" groups!");
//
//        for (int i=0; i< gNum; i++){
//            System.out.println("Found val "+matcher.group(i));
//        }
//
//    }
        String input = " (a) (a, b, 2) (2, a)";
        System.out.println(input.matches(TupleRegEx + "+"));

        input = "(a, b, 2)";
        System.out.println(input.matches(TupleRegEx.toString()));

        input = "approve (2, a)";
        System.out.println(input.matches(TableRegEx.toString()));

        input = " @0 approve (2, a) publish(7)";
        System.out.println(input.matches(TimeStamp.toString() + TableRegEx + "+"));

//    input="(2, a)(a,b)";
//    Scanner scan=new Scanner(input);
//    System.out.println("Has next tuple list?: "+scan.hasNext(TupleListRegEx));

        input = "(a, b)(2,a)publish(7)";
        Scanner scan = new Scanner(input);
        System.out.println("Has next tuple list?: " + scan.useDelimiter(TableRegEx).hasNext(TupleRegEx + "+"));
        System.out.println("Has next tuple list?: " + scan.useDelimiter(TableRegEx).hasNext(TupleListRegEx));

        String table = "(g)(7)";
        System.out.println(table + " is table? " + table.matches(TableRegEx.toString()));

        String table2 = "event(g)(7)";
        System.out.println(table2 + " is table? " + table2.matches(TableRegEx.toString()));

//    System.out.println("(2, a)(a,b) ".matches(TupleListRegEx));

//    input="@0 approve (2, a) publish(7)@1 approve (3,b)\n" +
//            "@5publish (3)(9)(77)approve (9,a) (77, d)";
//
//    System.out.println("The above expression matches ts-db list: "+ input.matches("("+TimeStamp+TableRegEx+"+)+"));
//
//
//    System.out.println("commit ([abc], 5, 2.2) matches table reg? "+ "commit (abc, 5, 22)".matches(TableRegEx));
//
//
//    input="@1272882926 commit (url1,1)\n" +
//            "commit (url10,1)\n" +
//            "commit (url11,1)";
//    scan=new Scanner(input);
//
//    System.out.println("Has next time stamp: "+scan.useDelimiter(Delim4FindingTimeStamp).hasNext(TimeStamp));
//
//    System.out.println(scan.next(TimeStamp) + " is the next ts!");
//
////    System.out.println("Has next event? "+scan.useDelimiter(RegHelper.Delim4FindingEvent).hasNext(RegHelper.EventName));
////    System.out.println(scan.next(EventName));
//
////    input="Good-._2A][:";
////    System.out.println(input+" matches string reg? "+input.matches(StringRegEx));
////
////    input="\" hi(!)\"";
////    System.out.println(input+" matches string reg? "+input.matches(StringRegEx));
//
//    input=" Good-._2A][: ";
//    System.out.println(input+" matches eventName reg? "+input.matches(EventName));
//
//    input=" \"Good-._2A][: \"";
//    System.out.println(input+" matches eventName reg? "+input.matches(EventName));
//
//
//    input=" \"Good-._2A][: \"";
//    System.out.println(input+" matches field reg? "+input.matches(FieldRegEx));
//
//    input=" \"Good-._2A][: \", abc";
//    System.out.println(input+" matches field list reg? "+input.matches(FieldsRegEx));
//
//    input=" ( 28u, abc ) ";
//    System.out.println(input+" matches tuple reg? "+input.matches(TupleRegEx));
//
//
//    input=" insert (script,db2,5,49887299) ";
//    System.out.println(input+" matches table reg? "+input.matches(TableRegEx));


    }
}
