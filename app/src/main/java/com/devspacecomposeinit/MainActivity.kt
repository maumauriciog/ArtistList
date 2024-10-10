package com.devspacecomposeinit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devspacecomposeinit.ui.theme.ComposeInitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeInitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val leonardo = Artist(
                        name = "Leonardo da Vinci",
                        lastSeenOnLine = "3days ago",
                        image = R.drawable.ic_leonardo_da_vinci,
                        cards = R.drawable.ic_mona_lisa
                    )
                    val pablo = Artist(
                        name = "Pablo Picasso",
                        lastSeenOnLine = "6days ago",
                        image = R.drawable.ic_pablo_picasso,
                        cards = R.drawable.ic_beijo
                    )
                    val salvador = Artist(
                        name = "Salvador Dali",
                        lastSeenOnLine = "8days ago",
                        image = R.drawable.ic_salvador_dali,
                        cards = R.drawable.ic_persistence_of_memory
                    )

                    val vicente = Artist(
                        name = "Vicent Vangovi",
                        lastSeenOnLine = "18days ago",
                        image = R.drawable.ic_vincent_van_gogh,
                        cards = R.drawable.ic_starry_night
                    )

                    val artists = listOf(
                        salvador, vicente,
                        leonardo, salvador, pablo, vicente, leonardo, salvador
                    )

                    LazyColumn {
                        items(artists) { artist ->
                            ArtistCard(artist,
                                onClick = {
                                    println("You clicked on the artist name ${artist.name}..")
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ArtistCard(
    artist: Artist,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = artist.image), contentDescription = "Artist Image"
            )
            Spacer(modifier = Modifier.size(7.dp))
            Column {
                Text(
                    text = artist.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = artist.lastSeenOnLine,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
        Card(
            modifier = Modifier
                .padding(6.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(235.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = artist.cards),
                contentDescription = "Cards"
            )
        }
    }
}

data class Artist(
    val name: String,
    val lastSeenOnLine: String,
    @DrawableRes val image: Int,
    @DrawableRes val cards: Int
)


@Preview(showBackground = true)
@Composable
fun ArtistCardPreview() {
    ComposeInitTheme {
        val newArtist = Artist(
            name = "Alfred Sisley",
            lastSeenOnLine = "3days ago",
            image = R.drawable.ic_leonardo_da_vinci,
            cards = R.drawable.ic_mona_lisa
        )
        ArtistCard(
            newArtist,
            onClick = {
                println("")
            }
        )
    }
}