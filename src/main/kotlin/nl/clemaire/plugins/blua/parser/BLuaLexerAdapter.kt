package nl.clemaire.plugins.blua.parser

import com.intellij.lexer.FlexAdapter

class BLuaLexerAdapter : FlexAdapter(BLuaLexer(null)) {
}
