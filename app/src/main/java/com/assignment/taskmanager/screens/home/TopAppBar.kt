package com.assignment.taskmanager.screens.home
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCustom(
    onLeftIconClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val backgroundColor = MaterialTheme.colorScheme.surfaceVariant
    val titleColor = MaterialTheme.colorScheme.primary
    val iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer
    val iconTintColor = MaterialTheme.colorScheme.onSecondaryContainer

    CenterAlignedTopAppBar(
        modifier = Modifier.padding(horizontal = 2.dp),
        navigationIcon = {
            ElevatedIcon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu",
                onClick = onLeftIconClick,
                backgroundColor = iconBackgroundColor,
                iconTint = iconTintColor
            )
        },
        title = {
            Text(
                text = "Task Manager",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                    color = titleColor
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        actions = {
            ElevatedIcon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Search",
                onClick = onSearchClick,
                backgroundColor = iconBackgroundColor,
                iconTint = iconTintColor
            )
            Spacer(modifier = Modifier.width(13.dp))
            ElevatedIcon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Profile",
                onClick = onProfileClick,
                backgroundColor = iconBackgroundColor,
                iconTint = iconTintColor
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor
        )
    )
}

@Composable
fun ElevatedIcon(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    iconTint: Color
) {
    Box(
        modifier = modifier
            .size(45.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                clip = false
            )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(8.dp),
            color = backgroundColor
        ) {
            IconButton(
                onClick = onClick,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = contentDescription,
                    tint = iconTint,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}
