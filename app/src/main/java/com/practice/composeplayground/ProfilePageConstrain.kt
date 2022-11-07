package com.practice.composeplayground

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@Composable
fun ProfilePageConstrain() {
    Card(
        elevation = 6.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
    ) {
        //Content of our card
        ConstraintLayout {
            val (image, nameText, countryText, rowStats, buttonFollow, buttonMessage) = createRefs()

            val guideLine = createGuidelineFromTop(0.1f)

            Image(
                painter = painterResource(id = R.drawable.fennec),
                contentDescription = "fox",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.Red, shape = CircleShape)
                    .constrainAs(image) {
                        top.linkTo(guideLine)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop
            )

            Text(text = "Fennec fox",
                modifier = Modifier.constrainAs(nameText) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(
                text = "Greenland",
                modifier = Modifier.constrainAs(countryText) {
                    top.linkTo(nameText.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .constrainAs(rowStats) {
                        top.linkTo(countryText.bottom)
                    }
            ) {
                ProfileStatsConstrain(count = "150", title = "Followers")
                ProfileStatsConstrain(count = "100", title = "Following")
                ProfileStatsConstrain(count = "50", title = "Posts")
            }

            Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(buttonFollow){
                top.linkTo(rowStats.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(buttonMessage.start)
                width = Dimension.wrapContent

            }) {
                Text(text = "Follow User")
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(buttonMessage){
                top.linkTo(rowStats.bottom, margin = 16.dp)
                start.linkTo(buttonFollow.end)
                end.linkTo(parent.end)
                width = Dimension.wrapContent

            }) {
                Text(text = "Direct Message")
            }
        }
    }
}


@Composable
fun ProfileStatsConstrain(count: String, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePageConstrainPreview() {
    ProfilePage()
}
