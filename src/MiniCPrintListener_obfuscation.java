import generated.MiniCBaseListener;
import generated.MiniCParser;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.HashMap;

public class MiniCPrintListener_obfuscation extends MiniCBaseListener implements ParseTreeListener{
    private static String output = "";
    ParseTreeProperty<String> cTree = new ParseTreeProperty<>();

    private static HashMap<String, String> nameMap = new HashMap<>();
    private static void assignObfuscatedName(String name){
        if(name.equals("main")) return;
        if(!(nameMap.containsKey(name)))
            nameMap.put(name, Obfuscator.obfuscateVariableName(name));
    }
    private static String getObfuscatedName(String name){
        if(name.equals("main")) return name;
        return nameMap.get(name);
    }

    public static String getOutput() {
        return output;
    }

    @Override public void exitProgram(MiniCParser.ProgramContext ctx) {
        StringBuilder program = new StringBuilder();
        for (int i = 0; i < ctx.decl().size(); i++)
            program.append(cTree.get(ctx.decl(i)));
        output = program.toString();
    }

    @Override public void exitDecl(MiniCParser.DeclContext ctx) {
        String result = "";

        if(ctx.fun_decl() != null) {
            result = cTree.get(ctx.fun_decl());
            cTree.put(ctx, result);
        }
        else {
            result = cTree.get(ctx.var_decl());
            cTree.put(ctx, result);
        }

    }

    @Override public void exitVar_decl(MiniCParser.Var_declContext ctx) {
        String type_spec = cTree.get(ctx.type_spec());
        String IDENT = ctx.IDENT().getText();
        String result = "";

        assignObfuscatedName(IDENT);
        String obfuscatedName = getObfuscatedName(IDENT);

        if(ctx.getChildCount() == 3)
            result = type_spec + " " + obfuscatedName + "; ";
        else if(ctx.getChildCount() == 5)
            result = type_spec + " " + obfuscatedName + " = " + ctx.LITERAL().getText() + "; ";
        else
            result = type_spec + " " + obfuscatedName + "[" + ctx.LITERAL().getText() + "]; ";

        cTree.put(ctx, result);
    }

    @Override public void exitType_spec(MiniCParser.Type_specContext ctx) {
        String result = "";

        if(ctx.VOID() != null)
            result = "void";
        else
            result = "int";

        cTree.put(ctx, result);
    }
    @Override public void exitFun_decl(MiniCParser.Fun_declContext ctx) {
        String type_spec = cTree.get(ctx.type_spec());
        String IDENT = ctx.IDENT().getText();
        String params = cTree.get(ctx.params());
        String compound_stmt = cTree.get(ctx.compound_stmt());
        String result = "";

        assignObfuscatedName(IDENT);
        String obfuscatedName = getObfuscatedName(IDENT);

        result = type_spec + " " + obfuscatedName + "(" + params + ")" + compound_stmt;

        cTree.put(ctx, result);
    }

    @Override public void exitParams(MiniCParser.ParamsContext ctx) {
        int count = ctx.getChildCount();

        if(count == 0)
            cTree.put(ctx, "");
        else if (ctx.VOID() != null)
            cTree.put(ctx, "void");
        else {
            String params = cTree.get(ctx.param(0));
            for(int i = 1; i < count; i+=2){
                params += ", " + cTree.get(ctx.param(i));
            }
            cTree.put(ctx, params);
        }
    }

    @Override public void exitParam(MiniCParser.ParamContext ctx) {
        String type_spec = cTree.get(ctx.type_spec());
        String IDENT = ctx.IDENT().getText();

        assignObfuscatedName(IDENT);
        String obfuscatedName = getObfuscatedName(IDENT);

        String result = type_spec + " " + obfuscatedName;

        if(ctx.getChildCount() == 3)
            result += "[]";

        cTree.put(ctx, result);
    }

    @Override public void exitStmt(MiniCParser.StmtContext ctx) {
        String result = "";

        if(ctx.expr_stmt() != null)
            result = cTree.get(ctx.expr_stmt());
        else if(ctx.compound_stmt() != null)
            result = cTree.get(ctx.compound_stmt());
        else if(ctx.if_stmt() != null)
            result = cTree.get(ctx.if_stmt());
        else if(ctx.while_stmt() != null)
            result = cTree.get(ctx.while_stmt());
        else
            result = cTree.get(ctx.return_stmt());

        cTree.put(ctx, result);
    }

    @Override public void exitExpr_stmt(MiniCParser.Expr_stmtContext ctx) {
        String result = cTree.get(ctx.expr()) + "; ";
        cTree.put(ctx, result);
    }

    @Override public void exitWhile_stmt(MiniCParser.While_stmtContext ctx) {
        String result = "while(" + cTree.get(ctx.expr()) + ")" + cTree.get(ctx.stmt());
        cTree.put(ctx, result);
    }

    @Override public void enterCompound_stmt(MiniCParser.Compound_stmtContext ctx) {

    }
    @Override public void exitCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < ctx.local_decl().size(); i++)
            result.append(cTree.get(ctx.local_decl(i)));
        for(int i = 0; i < ctx.stmt().size(); i++)
            result.append(cTree.get(ctx.stmt(i)));

        cTree.put(ctx, " " + "{ " + result.toString() + "} ");
    }

    @Override public void exitLocal_decl(MiniCParser.Local_declContext ctx) {
        String type_spec = cTree.get(ctx.type_spec());
        String IDENT = ctx.IDENT().getText();
        String result = "";

        assignObfuscatedName(IDENT);
        String obfuscatedName = getObfuscatedName(IDENT);

        if(ctx.getChildCount() == 3)
            result = type_spec + " " + obfuscatedName + "; ";
        else if(ctx.getChildCount() == 5)
            result = type_spec + " " + obfuscatedName + " = " + ctx.LITERAL().getText() + "; ";
        else
            result = type_spec + " " + obfuscatedName + "[" + ctx.LITERAL().getText() + "]; ";


        cTree.put(ctx, result);
    }

    @Override public void exitIf_stmt(MiniCParser.If_stmtContext ctx) {
        String expr = cTree.get(ctx.expr());
        String stmt1 = cTree.get(ctx.stmt(0));
        String result = "";

        if(ctx.getChildCount() == 5)
            result = "if(" + expr + ")" + stmt1;
        else
            result = "if(" + expr + ")" + stmt1 + "else" + cTree.get(ctx.stmt(1));

        cTree.put(ctx, result);
    }

    @Override public void exitReturn_stmt(MiniCParser.Return_stmtContext ctx) {
        String result = "";

        if(ctx.getChildCount() == 2)
            result = "return; ";
        else
            result = "return " + cTree.get(ctx.expr()) + "; ";

        cTree.put(ctx, result);
    }

    @Override
    public void exitExpr(MiniCParser.ExprContext ctx) {
        String result = "";
        int childCount = ctx.getChildCount();
        String obfuscatedName = "";

        if(ctx.IDENT() != null) {
            assignObfuscatedName(ctx.IDENT().getText());
            obfuscatedName = getObfuscatedName(ctx.IDENT().getText());
        }

        if(childCount == 1) {
            if(ctx.IDENT() != null)
                result = obfuscatedName;
            else
                result = ctx.getChild(0).getText();
        }
        else if(childCount == 2){
            result = ctx.getChild(0).getText() + cTree.get(ctx.expr(0));
        }
        else if(childCount == 3){
            if(ctx.getChild(0).getText().equals("("))
                result = "(" + cTree.get(ctx.expr(0)) + ")";
            else if(ctx.getChild(1).getText().equals("="))
                result = obfuscatedName + " = " + cTree.get(ctx.expr(0));
            else {
                result = cTree.get(ctx.expr(0)) + " " +ctx.getChild(1).getText() + " " + cTree.get(ctx.expr(1));
            }
        }
        else if (childCount == 4) {
            if(ctx.getChild(1).getText().equals("("))
                result = obfuscatedName + "(" + cTree.get(ctx.args()) + ")";
            else
                result = obfuscatedName + "[" + cTree.get(ctx.expr(0)) + "]";
        }
        else {
            result = obfuscatedName + "[" + cTree.get(ctx.expr(0)) + "] = " + cTree.get(ctx.expr(1));
        }

        cTree.put(ctx, result);
    }

    @Override public void exitArgs(MiniCParser.ArgsContext ctx) {
        int count = ctx.getChildCount();

        if(count == 0)
            cTree.put(ctx, "");
        else {
            String args = cTree.get(ctx.expr(0));
            for(int i = 1; i < count; i+=2){
                args += ", " + cTree.get(ctx.expr(i));
            }
            cTree.put(ctx, args);
        }
    }
}

