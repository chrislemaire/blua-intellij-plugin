package nl.clemaire.plugins.blua.ast

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import nl.clemaire.plugins.blua.BLuaFileType
import nl.clemaire.plugins.blua.BLuaFile

object BLuaElementFactory {
    /**
     * Creates a BLuaId elements in the current project with the given name.
     */
    fun createIdToken(project: Project, name: String): PsiElement {
        val file = createFile(project, "function $name() end")
        return (file.children[0] as BLuaNamedFunctionStmt).getIdToken()!!
    }

    /**
     * Creates a VarRef expression in the current project with the given name.
     */
    fun createVarRef(project: Project, name: String): BLuaVarRefExpr =
        createExpr(project, name)

    /**
     * Creates an expression in the current project from the given string.
     */
    inline fun <reified T : BLuaExpr> createExpr(project: Project, expr: String): T {
        val file = createFile(project, "a = $expr")
        return (file.children[0] as BLuaAssignStmt).exprs.exprList[0] as T
    }

    fun createFile(project: Project, text: String): BLuaFile =
        PsiFileFactory.getInstance(project).createFileFromText(
            "dummy.${BLuaFileType.defaultExtension}", BLuaFileType, text) as BLuaFile
}
