package com.anu.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.anu.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LazySimpleList()
                }
            }
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(
        modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { }
            .background(color = MaterialTheme.colors.surface)
            .padding(8.dp)) {
        Surface(
            shape = CircleShape,
            modifier = Modifier.size(50.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
        ) {
//       Image(painter = , contentDescription = )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(CenterVertically)
        ) {
            Text(text = "Anusree Sajeevan", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "3 mins ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview
@Composable
fun PhotographerCardPreview() {
    MyApplicationTheme {
        PhotographerCard()
    }
}

@Composable
fun CustomiseButtons() {
    Button(onClick = { }) {
        Text(text = "Like")
        Icon(
            modifier = Modifier.padding(start = 4.dp),
            painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
            contentDescription = null
        )
    }
}

@Composable
@Preview
fun PreviewCustomiseButtons() {
    CustomiseButtons()
}


@Composable
fun ExploreLayouts() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "This is topbar") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPAdding ->
        BodyContent(Modifier.padding(innerPAdding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi There!")
        Text(text = "Thanks for material design composable!!")
    }
}

@Preview
@Composable
fun PreviewExploreLayouts() {
    MyApplicationTheme {
        ExploreLayouts()
    }
}

@Composable
fun SimpleList() {
    val scrollState = rememberScrollState()
    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100000) {
            Text(text = "Item$it")
        }
    }
}
@Composable
fun LazySimpleList() {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val listSize = 100000

    Column {
        Row {
            Button(onClick = { coroutineScope.launch { scrollState.animateScrollToItem(0) } }) {
                Text(text = "Scroll to top")
            }

            Button(onClick = { coroutineScope.launch { scrollState.animateScrollToItem(listSize - 1) } }) {
                Text(text = "Scroll to bottom")
            }
        }


        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(pos = it)
            }
        }
    }
}

@Composable
fun ImageListItem(pos: Int) {
    Row {
        Image(painter = rememberImagePainter(data = "https://developer.android.com/images/brand/Android_Robot.png"), contentDescription = null,
        modifier = Modifier.size(50.dp))
        Text(text = "Item$pos")
    }

}
