package nl.clemaire.plugins.blua.braces

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import nl.clemaire.plugins.blua.ast.BLuaTypes.*
import nl.clemaire.plugins.blua.ast.BLuaTypes.C_MUST_CLOSE
import nl.clemaire.plugins.blua.ast.BLuaTypes.C_MUST_OPEN

class BLuaPairedBraceMatcher : PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> = arrayOf(
        BracePair(C_MUST_OPEN, C_MUST_CLOSE, true),
        BracePair(C_PAR_OPEN, C_PAR_CLOSE, false),
        BracePair(C_SQR_OPEN, C_SQR_CLOSE, false)
    )

    override fun isPairedBracesAllowedBeforeType(
        lbraceType: IElementType,
        contextType: IElementType?
    ): Boolean = true

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int =
        openingBraceOffset
}
