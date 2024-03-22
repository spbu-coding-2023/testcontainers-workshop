package org.example.repositories

import org.example.entities.User
import org.springframework.data.repository.CrudRepository

interface UsersRepository: CrudRepository<User, Long>
