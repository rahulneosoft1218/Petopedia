package com.petopedia.app.domain.model

enum class Statistic(
    val count: Int,
) {
    MAX(3),
}

enum class Animal(
    val species: Int,
) {
    Cat(1),
    Dog(2),
    Sheep(3),
    Rabbit(4),
    Parrot(5),
}
