package br.com.meumetro.enums

import br.com.meumetro.extensions.removeAccent

enum class LineType(
        val value: String,
        val nameLine: String,
        val nameFormatted: String
) {

    LINE_1_BLUE("1", "Azul", "Linha 1 Azul"),
    LINE_2_GREEN("2", "Verde", "Linha 2 Verde"),
    LINE_3_RED("3", "Vermelha", "Linha 3 Vermelha"),
    LINE_4_YELLOW("4", "Amarela", "Linha 4 Amarela"),
    LINE_5_LILAC("5", "Lilas", "Linha 5 Lilás"),
    LINE_7_RUBY("7", "Rubi", "Linha 7 Rubi"),
    LINE_8_DIAMOND("8", "Diamante", "Linha 8 Diamante"),
    LINE_9_EMERALD("9", "Esmeralda", "Linha 9 Esmeralda"),
    LINE_10_TURQUOISE("10", "Turquesa", "Linha 10 Turquesa"),
    LINE_11_CORAL("11", "Coral", "Linha 11 Coral"),
    LINE_12_SAPPHIRE("12", "Safira", "Linha 12 Safira"),
    LINE_13_JADE("13", "Jade", "Linha 13 Jade"),
    LINE_15_SILVER("15", "Prata", "Linha 15 Prata");

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
    }
}