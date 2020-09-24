package com.thescore.avikd.nbateamviewer.util
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.Player
import com.thescore.avikd.nbateamviewer.nbateamsapi.data.Team

object DomainObjectFactory {

    private val playerList = arrayListOf<Player>(Player(firstName = "Test", lastName = "LastName", position = "SG", number = 12, id = 1))
    private fun createTeam() = Team(1, "Boston Celtics",  (1..100).random(),  (1..100).random(), playerList)

    fun createTeamLists(count: Int): List<Team> {
        return (0 until count).map {
            createTeam()
        }
    }

}
