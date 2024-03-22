package org.example.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var surname: String,
    @Column(nullable = false)
    var email: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
) {
    override fun toString() = "User(#$id | $name $surname | $email)"
}
