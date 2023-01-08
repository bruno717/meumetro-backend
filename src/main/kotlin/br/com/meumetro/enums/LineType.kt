package br.com.meumetro.enums

import br.com.meumetro.extensions.removeAccent

enum class LineType(
    val value: String,
    val nameLine: String,
    val nameFormatted: String,
    val codeClassCss: String
) {

    LINE_1_BLUE("1", "Azul", "Linha 1 Azul", "line-1"),
    LINE_2_GREEN("2", "Verde", "Linha 2 Verde", "line-2"),
    LINE_3_RED("3", "Vermelha", "Linha 3 Vermelha", "line-3"),
    LINE_4_YELLOW("4", "Amarela", "Linha 4 Amarela", "line-4"),
    LINE_5_LILAC("5", "Lilas", "Linha 5 Lilás", "line-5"),
    LINE_7_RUBY("7", "Rubi", "Linha 7 Rubi", "line-7"),
    LINE_8_DIAMOND("8", "Diamante", "Linha 8 Diamante", "line-8"),
    LINE_9_EMERALD("9", "Esmeralda", "Linha 9 Esmeralda", "line-9"),
    LINE_10_TURQUOISE("10", "Turquesa", "Linha 10 Turquesa", "line-10"),
    LINE_11_CORAL("11", "Coral", "Linha 11 Coral", "line-11"),
    LINE_12_SAPPHIRE("12", "Safira", "Linha 12 Safira", "line-12"),
    LINE_13_JADE("13", "Jade", "Linha 13 Jade", "line-13"),
    LINE_15_SILVER("15", "Prata", "Linha 15 Prata", "line-15");

    companion object {
        fun getLineType(type: Int): LineType? {
            return values().firstOrNull { it.value == type.toString() }
        }

        fun getLineType(type: String): LineType? {
            return values().firstOrNull { it.toString() == type }
        }

        fun getLineTypeByName(name: String): LineType? {
            val nameWithoutAccent = name.removeAccent()
            return values().firstOrNull { it.nameLine.equals(nameWithoutAccent, true) }
        }

        fun getLineTypeByCode(code: String): LineType? {
            return values().firstOrNull { it.value.equals(code, true) }
        }

        fun getCompanyType(type: LineType): String {
            return when (type) {
                LINE_4_YELLOW -> "4"
                LINE_7_RUBY,
                LINE_8_DIAMOND,
                LINE_9_EMERALD,
                LINE_10_TURQUOISE,
                LINE_11_CORAL,
                LINE_12_SAPPHIRE,
                LINE_13_JADE -> "c"
                else -> "m"
            }
        }
    }
}