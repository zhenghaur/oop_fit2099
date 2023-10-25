package game.utils;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    INVINCIBLE, // use this status to tell that current instance is invincible.
    DORMANT, // use this status to tell that current instance is dormant.
    RESET, // use this status to tell that game has been reset.
    CAPABLE_DORMANT, // use this status to tell if current instance is capable to become dormant
    FLY, // use this status to tell if current instance is able to fly
    FIRE_ATTACK, // use this status to tell if current instance has fire attack
}
