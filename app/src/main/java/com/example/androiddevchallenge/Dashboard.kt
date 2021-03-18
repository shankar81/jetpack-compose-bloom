package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class Theme(val name: String, val image: Int)

class Plant(
    val name: String,
    val image: Int,
    val description: String = "This is description",
)

val themes = listOf(
    Theme("Desert chic", R.drawable.theme1),
    Theme("Tiny terrariums", R.drawable.theme2),
    Theme("Jungle vibes", R.drawable.theme3),
    Theme("Easy care", R.drawable.theme4),
    Theme("Statements", R.drawable.theme5),
)

val plants = listOf(
    Plant("Monstera", R.drawable.plant1),
    Plant("Aglaonema", R.drawable.plant2),
    Plant("Peace lily", R.drawable.plant3),
    Plant("Fiddle leaf tree", R.drawable.plant4),
    Plant("Snake plant", R.drawable.plant5),
    Plant("Pothos", R.drawable.plant6),
)

class Dashboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DashboardContainer(modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

@Composable
fun DashboardContainer(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        LoginInput(
            modifier = Modifier.padding(vertical = 16.dp),
            placeholder = "Search",
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search"
                )
            })
        Heading(label = "Browse themes")
        ThemeList()
        Heading(
            label = "Design your home garden",
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        PlantList()
    }
}

@Composable
fun Heading(modifier: Modifier = Modifier, label: String) {
    Text(
        modifier = modifier,
        text = label,
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun ThemeList(modifier: Modifier = Modifier) {
    LazyRow(modifier) {
        items(themes.size) {
            ThemeCard(
                Modifier
                    .padding(vertical = 8.dp)
                    .padding(end = 16.dp),
                name = themes[it].name,
                image = themes[it].image
            )
        }
    }
}

@Composable
fun ThemeCard(modifier: Modifier = Modifier, name: String, image: Int) {
    Card(
        modifier = modifier
            .size(140.dp)
            .clickable {},
        backgroundColor = MaterialTheme.colors.onSecondary,
        shape = RoundedCornerShape(4.dp),
        elevation = 6.dp,
    ) {
        Column {
            CoilImage(
                modifier = Modifier.weight(0.73f),
                data = image,
                contentDescription = name,
                contentScale = ContentScale.FillBounds
            )
            Text(
                modifier = Modifier
                    .weight(0.27f)
                    .padding(horizontal = 16.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically),
                text = name,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun PlantList(modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(plants.size) { index ->
            PlantConstraintItem(
                Modifier
                    .padding(bottom = 8.dp),
                name = plants[index].name,
                image = plants[index].image,
                description = plants[index].description,
            )
        }
    }
}

// Highly nested layout
@Composable
fun PlantItem(
    modifier: Modifier = Modifier,
    name: String,
    image: Int,
    description: String,
) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier
            .height(75.dp)
    ) {
        CoilImage(
            modifier = Modifier
                .weight(0.20f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(8.dp)),
            data = image,
            contentDescription = name,
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = modifier.weight(0.05f))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.75f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = description,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                BloomCheckbox(isChecked = isChecked) { isChecked = it }
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = MaterialTheme.colors.onBackground,
                thickness = 3.dp
            )
        }
    }
}

// Less nested layout
@Composable
fun PlantConstraintItem(
    modifier: Modifier = Modifier,
    name: String,
    image: Int,
    description: String,
) {
    var isChecked by remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(bottom = 8.dp, end = 16.dp),
        constraintSet = plantConstraints()
    ) {
        CoilImage(
            modifier = Modifier
                .layoutId("image")
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            data = image,
            contentDescription = name,
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .layoutId("content")
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onBackground,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
            )
        }
        Divider(
            modifier = Modifier
                .layoutId("divider"),
            thickness = 1.dp,
            startIndent = 45.dp
        )
        BloomCheckbox(modifier = Modifier.layoutId("checkbox"), isChecked = isChecked) {
            isChecked = it
        }
    }
}

private fun plantConstraints(): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val content = createRefFor("content")
        val divider = createRefFor("divider")
        val checkbox = createRefFor("checkbox")

        constrain(image) {
            start.linkTo(parent.start)
        }

        constrain(content) {
            start.linkTo(image.end, margin = 8.dp)
            centerVerticallyTo(parent)
        }

        constrain(divider) {
            start.linkTo(content.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }

        constrain(checkbox) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
    }
}

@Composable
fun BloomCheckbox(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)? = null
) {
    Checkbox(modifier = modifier, checked = isChecked, onCheckedChange = onCheckedChange)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    MyTheme {
        DashboardContainer()
    }
}