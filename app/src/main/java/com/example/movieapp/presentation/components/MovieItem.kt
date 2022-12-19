package com.example.movieapp.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.movieapp.domain.model.Movie
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun MovieItem(
    movie:Movie,
) {
    var isClicked by remember {
        mutableStateOf(false)
    }
    val extraPadding by animateDpAsState(
        if (isClicked) 50.dp else 0.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy
            , stiffness = Spring.StiffnessLow)
    )
    val cast = movie.Cast.split("|")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                isClicked = !isClicked
            },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
            Column(modifier = Modifier.padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) { 
                    SubcomposeAsyncImage(
                        model = movie.Movie_Poster,
                        contentDescription = "movie poster",
                        loading = {
                            CircularProgressIndicator(modifier = Modifier.scale(0.5f))
                        }, modifier = Modifier.size(150.dp,150.dp).clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop)

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.align(CenterVertically)) {
                        Text(
                            text = movie.Title,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                            color = MaterialTheme.colors.surface,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.body1,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = movie.Rating,
                            modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                            color = Color.Yellow,
                            fontWeight = FontWeight.Bold, fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Director: ${movie.Director}",
                            modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                            color = MaterialTheme.colors.surface
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Runtime: ${movie.Runtime} mins",
                            modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                            color = MaterialTheme.colors.surface
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Year: ${movie.Year} ",
                            modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                            color = MaterialTheme.colors.surface
                        )
                    }
                }
                FlowRow(
                    mainAxisSpacing = 10.dp,
                    crossAxisSpacing = 10.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    cast.forEach {
                        Cast(cast = it)
                    }
                }
                if (isClicked) {
                    Text(
                        text = "Summary",
                        modifier = Modifier.padding(20.dp, 0.dp, 12.dp, 0.dp),
                        color = MaterialTheme.colors.surface,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = movie.Summary,
                        modifier = Modifier.padding(20.dp, 0.dp, 12.dp, 0.dp),
                        color = MaterialTheme.colors.surface,
                        style = MaterialTheme.typography.body1,
                        softWrap = true
                    )
                }
            }
        }
}

