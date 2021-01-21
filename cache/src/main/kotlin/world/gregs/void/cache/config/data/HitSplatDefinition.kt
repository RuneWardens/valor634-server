package world.gregs.void.cache.config.data

import world.gregs.void.cache.Definition

/**
 * @author GregHib <greg@gregs.world>
 * @since April 07, 2020
 */
data class HitSplatDefinition(
    override var id: Int = -1,
    var font: Int = -1,
    var textColour: Int = 16777215,
    var icon: Int = -1,
    var left: Int = -1,
    var middle: Int = -1,
    var right: Int = -1,
    var offsetX: Int = 0,
    var amount: String = "",
    var duration: Int = 70,
    var offsetY: Int = 0,
    var fade: Int = -1,
    var comparisonType: Int = -1,
    var anInt3214: Int = 0
) : Definition