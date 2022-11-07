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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@Composable
fun ProfilePage() {
    Card(
        elevation = 6.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
    ) {
        //Content of our card
        BoxWithConstraints() {
            val constraints = if (minWidth < 600.dp) {
                portraitConstrains(margin = 16.dp)
            } else {
                landscapeConstrains(margin = 16.dp)
            }

            ConstraintLayout(constraints) {
                Image(
                    painter = painterResource(id = R.drawable.fennec),
                    contentDescription = "fox",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(width = 2.dp, color = Color.Red, shape = CircleShape)
                        .layoutId("image"),
                    contentScale = ContentScale.Crop
                )

                Text(text = "Fennec fox", Modifier.layoutId("nameText"))
                Text(text = "Greenland", Modifier.layoutId("countryText"))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("rawStats")
                ) {
                    ProfileStats(count = "150", title = "Followers")
                    ProfileStats(count = "100", title = "Following")
                    ProfileStats(count = "50", title = "Posts")
                }

                Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("buttonFollow")) {
                    Text(text = "Follow User")
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("buttonMessage")) {
                    Text(text = "Direct Message")
                }
            }
        }
    }
}


@Composable
fun ProfileStats(count: String, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

private fun portraitConstrains(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val countryText = createRefFor("countryText")
        val rowStats = createRefFor("rawStats")
        val buttonFollow = createRefFor("buttonFollow")
        val buttonMessage = createRefFor("buttonMessage")
        val guideLine = createRefFor("guideLine")

        constrain(image) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(nameText) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(countryText) {
            top.linkTo(nameText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowStats) {
            top.linkTo(countryText.bottom)
        }
        constrain(buttonFollow) {
            top.linkTo(rowStats.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(buttonMessage.start)
            width = Dimension.wrapContent
        }
        constrain(buttonMessage) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(buttonFollow.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }
        constrain(guideLine) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(buttonFollow.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }
    }
}

private fun landscapeConstrains(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val countryText = createRefFor("countryText")
        val rowStats = createRefFor("rawStats")
        val buttonFollow = createRefFor("buttonFollow")
        val buttonMessage = createRefFor("buttonMessage")
        val guideLine = createRefFor("guideLine")


        constrain(image) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }
        constrain(nameText) {
            start.linkTo(image.start)
            top.linkTo(image.bottom)
        }
        constrain(countryText) {
            top.linkTo(nameText.bottom)
            start.linkTo(nameText.start)
            end.linkTo(nameText.end)
        }
        constrain(rowStats) {
            top.linkTo(image.top)
            start.linkTo(image.end, margin = margin)
            end.linkTo(parent.end)
        }

        constrain(buttonFollow) {
            top.linkTo(rowStats.bottom, margin = 16.dp)
            start.linkTo(rowStats.start)
            end.linkTo(buttonMessage.start)
            bottom.linkTo(countryText.bottom)
            width = Dimension.wrapContent
        }
        constrain(buttonMessage) {
            top.linkTo(rowStats.bottom, margin = 16.dp)
            start.linkTo(buttonFollow.end)
            end.linkTo(parent.end)
            bottom.linkTo(countryText.bottom)
            width = Dimension.wrapContent
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}
