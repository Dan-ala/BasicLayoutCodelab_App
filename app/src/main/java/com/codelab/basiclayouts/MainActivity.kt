package com.codelab.basiclayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basiclayouts.ui.theme.MySootheTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            MySootheApp(windowSizeClass)
        }
    }
}

// Step: Search bar - Modifiers
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

// Step: Align your body - Alignment
@Composable
fun AlignYourBodyElement(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    @DrawableRes drawable: Int,
    @StringRes title: Int
) {
    // Implement composable here
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        Text(
            text = stringResource(id = title),
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = text),
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

// Step: Favorite collection card - Material Surface
@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    @DrawableRes drawable: Int
) {
    // Implement composable here
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ){
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

// Step: Align your body row - Arrangements
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(22.dp),
        contentPadding = PaddingValues(horizontal = 30.dp),
        modifier = modifier
    ){
        items(alignYourBodyData){ item ->
            AlignYourBodyElement(drawable = item.drawable, text = item.text, title = item.title)
        }
    }
}

// Step: Favorite collections grid - LazyGrid
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.height(168.dp)
    ){
        items(favoriteCollectionsData){ item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                text = item.text,
                modifier = modifier.height(80.dp))
        }
    }
}

// Step: Home section - Slot APIs
@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    // Implement composable here
    Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 40.dp)
                .padding(horizontal = 16.dp)

        )
        content()
    }
}

// Step: Home screen - Scrolling
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // Implement composable here
    Column(
        modifier
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

// Step: Bottom navigation - Material
@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    // Implement composable here
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ){
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.bottom_navigation_profile)
                )
            },
            selected = true,
            onClick = {}
        )
    }
}

// Step: MySoothe App - Scaffold
@Composable
fun MySootheAppPortrait() {
    // Implement composable here
    MySootheTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

// Step: Bottom navigation - Material
@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    // Implement composable here
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background
    ){
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        stringResource(id = R.string.bottom_navigation_home)
                    )
                },
                selected = true,
                onClick = {}
            )

            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {}
            )
        }
    }
}

// Step: Landscape Mode
@Composable
fun MySootheAppLandscape(){
    // Implement composable here
    MySootheTheme {
        Surface(color = MaterialTheme.colorScheme.background){
            Row{
                SootheNavigationRail()
                HomeScreen()
            }
        }
    }
}

// Step: MySoothe App
@Composable
fun MySootheApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MySootheAppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            MySootheAppLandscape()
        }
    }
}

private val alignYourBodyData = listOf(
    DrawableStringPair(R.drawable.sencilla, R.string.sencilla, R.string.sencilla_title),
    DrawableStringPair(R.drawable.con_queso, R.string.con_queso, R.string.con_queso_title),
    DrawableStringPair(R.drawable.con_chorizo, R.string.con_chorizo, R.string.con_chorizo_title),
    DrawableStringPair(R.drawable.jamon_queso, R.string.jamon_queso, R.string.jamon_queso_title),
    DrawableStringPair(R.drawable.huevos_pericos, R.string.huevos_pericos, R.string.huevos_pericos_title),
    DrawableStringPair(R.drawable.mixta_sencilla, R.string.mixta_sencilla, R.string.mixta_sencilla_title),
    DrawableStringPair(R.drawable.mixta_especial, R.string.mixta_especial, R.string.mixta_especial_title),
    DrawableStringPair(R.drawable.mixta_casa, R.string.mixta_casa, R.string.mixta_casa_title),
    DrawableStringPair(R.drawable.super_arepa, R.string.super_arepa, R.string.super_arepa_title),
    DrawableStringPair(R.drawable.choriperro_esp, R.string.choriperro_esp, R.string.choriperro_esp_title),
    DrawableStringPair(R.drawable.mexicana, R.string.mexicana, R.string.mexicana_title),
    DrawableStringPair(R.drawable.hot_dog, R.string.hot_dog, R.string.hot_dog_title),
    DrawableStringPair(R.drawable.choriperro_sen, R.string.choriperro_sen, R.string.choriperro_sen_title),
    DrawableStringPair(R.drawable.pincho, R.string.pincho, R.string.pincho_title),
    DrawableStringPair(R.drawable.tinto, R.string.tinto, R.string.tinto_title),
    DrawableStringPair(R.drawable.coke, R.string.coke, R.string.coke_title)
).map { it }

private val favoriteCollectionsData = listOf(
    R.drawable.arepas_category to R.string.arepas_category,
    R.drawable.variedades_category to R.string.variedades_category,
    R.drawable.bebidas_category to R.string.bebidas_category,
    R.drawable.hot_dog_category to R.string.hot_dog_category,
    R.drawable.mexican_category to R.string.mexican_category,
    R.drawable.pinchos_category to R.string.pinchos_category
).map { DrawableStringPair2(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    @StringRes val title: Int
)

private data class DrawableStringPair2(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun SearchBarPreview() {
//    MySootheTheme { SearchBar(Modifier.padding(8.dp)) }
//}

//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun AlignYourBodyElementPreview() {
//    MySootheTheme {
//        AlignYourBodyElement(
//            text = R.string.sencilla,
//            drawable = R.drawable.sencilla,
//            title = R.string.sencilla_title,
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//}

//@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
//@Composable
//fun FavoriteCollectionCardPreview() {
//    MySootheTheme {
//        FavoriteCollectionCard(
//            text = R.string.fc2_nature_meditations,
//            drawable = R.drawable.fc2_nature_meditations,
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionsGridPreview() {
    MySootheTheme { FavoriteCollectionsGrid() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyRowPreview() {
    MySootheTheme { AlignYourBodyRow() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    MySootheTheme {
        HomeSection(R.string.align_your_body){
            AlignYourBodyRow()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun ScreenContentPreview() {
    MySootheTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BottomNavigationPreview() {
    MySootheTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun NavigationRailPreview() {
    MySootheTheme { SootheNavigationRail() }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MySoothePortraitPreview() {
    MySootheAppPortrait()
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
fun MySootheLandscapePreview() {
    MySootheAppLandscape()
}