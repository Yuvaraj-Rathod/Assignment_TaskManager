package com.assignment.taskmanager.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.*


@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        NavigationItem.Home,
        NavigationItem.Profile,
        NavigationItem.Settings
    )

    val currentRoute by navController.currentBackStackEntryAsState()
    val selectedIndex = navigationItems.indexOfFirst { it.route == currentRoute?.destination?.route }
        .takeIf { it != -1 } ?: 0

    val backgroundColor = MaterialTheme.colorScheme.surfaceVariant
    val selectedColor = MaterialTheme.colorScheme.primary
    val unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        containerColor = backgroundColor
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    if (currentRoute?.destination?.route != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(26.dp),
                        tint = if (selectedIndex == index) selectedColor else unselectedColor
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}

sealed class NavigationItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : NavigationItem("Home", Icons.Default.Home, "home")
    object Profile : NavigationItem("Profile", Icons.Default.Person, "profile")
    object Settings : NavigationItem("Settings", Icons.Default.Settings, "settings")
}
