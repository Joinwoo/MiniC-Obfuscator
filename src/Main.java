import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.ParseTree;
import generated.*;

public class Main{
    public static void main(String[] args) throws Exception{
        CharStream code = CharStreams.fromFileName("./src/test.c");
        MiniCLexer lexer = new MiniCLexer(code);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        try{
            MiniCParser parser = new MiniCParser(tokens);
            ParseTree tree = parser.program();

            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(new MiniCPrintListener_obfuscation(), tree);

            System.out.println(MiniCPrintListener_obfuscation.getOutput());
        } catch (RuntimeException e){
            System.out.println("Error");
        }
    }
}