package com.example.movieappmad24.navigation

sealed class Screens {
    companion object {
        data object HomeScreenDestination : ScreenDestination {
            override val route = "homescreen"
            override val argument = ""
        }

        data object DetailScreenDestination: ScreenDestination {
            override val route = "detailscreen"
            override val argument = "movieId"
        }

        data object WatchlistScreenDestination: ScreenDestination {
            override val route = "watchlistscreen"
            override val argument = ""
        }
    }
}

sealed interface ScreenDestination {
    val route: String
    val argument: String
    val routeWithArgument: String
        get() = "$route/{$argument}"

    fun navigate(argument: String) = "$route/$argument"
}