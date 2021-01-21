package world.gregs.void.engine.entity.character.npc

import world.gregs.void.cache.definition.data.NPCDefinition
import world.gregs.void.engine.action.Action
import world.gregs.void.engine.entity.Size
import world.gregs.void.engine.entity.character.Character
import world.gregs.void.engine.entity.character.CharacterEffects
import world.gregs.void.engine.entity.character.CharacterValues
import world.gregs.void.engine.entity.character.move.Movement
import world.gregs.void.engine.entity.character.update.LocalChange
import world.gregs.void.engine.entity.character.update.Visuals
import world.gregs.void.engine.entity.definition.NPCDefinitions
import world.gregs.void.engine.map.Tile
import world.gregs.void.engine.path.TargetStrategy
import world.gregs.void.utility.get

/**
 * A non-player character
 * @author GregHib <greg@gregs.world>
 * @since March 28, 2020
 */
data class NPC(
    override val id: Int,
    override var tile: Tile,
    override val size: Size = Size.TILE,
    override val visuals: Visuals = Visuals(),
    override val movement: Movement = Movement(tile.minus(1)),
    override val action: Action = Action(),
    override val values: CharacterValues = CharacterValues()
) : Character {

    override val effects = CharacterEffects()

    init {
        effects.link(this)
    }

    override var change: LocalChange? = null
    var walkDirection: Int = -1
    var runDirection: Int = -1

    var movementType: NPCMoveType = NPCMoveType.None

    @Transient
    override lateinit var interactTarget: TargetStrategy

    val def: NPCDefinition
        get() = get<NPCDefinitions>().get(id)

    constructor(id: Int = 0, tile: Tile = Tile.EMPTY, index: Int) : this(id, tile) {
        this.index = index
    }

    override var index: Int = -1

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as NPC
        return index == other.index
    }

    override fun hashCode(): Int {
        return index
    }

    override fun toString(): String {
        return "NPC(id=$id, index=$index, tile=$tile)"
    }
}