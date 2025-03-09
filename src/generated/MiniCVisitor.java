// Generated from C:/Users/user/IdeaProjects/PrettyPrint/MiniC.g4 by ANTLR 4.13.2
package generated;
 


import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiniCVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MiniCParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MiniCParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(MiniCParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#var_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_decl(MiniCParser.Var_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#type_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_spec(MiniCParser.Type_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#fun_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFun_decl(MiniCParser.Fun_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(MiniCParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(MiniCParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(MiniCParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#expr_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_stmt(MiniCParser.Expr_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(MiniCParser.While_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound_stmt(MiniCParser.Compound_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#local_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocal_decl(MiniCParser.Local_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(MiniCParser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(MiniCParser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(MiniCParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniCParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(MiniCParser.ArgsContext ctx);
}