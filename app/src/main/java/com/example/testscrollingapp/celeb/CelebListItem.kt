package com.example.testscrollingapp.celeb

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.testscrollingapp.repository.model.Celebrity
import com.example.testscrollingapp.repository.model.knownForAsStringMax3
import com.example.testscrollingapp.ui.theme.LightBlue
import com.example.testscrollingapp.ui.theme.TestScrollingAppTheme

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w235_and_h235_face"

@Composable
fun CelebListItem(
    celebrity: Celebrity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        modifier = modifier
            .clickable(onClick = onClick),
        color = LightBlue.copy(alpha = 0.2f)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {
                Row(modifier = Modifier.fillMaxHeight()) {
                    AsyncImage(
                        model = BASE_IMAGE_URL + celebrity.profilePath,
                        contentDescription = null,
                        modifier = Modifier
                            .size(128.dp)
                            .clip(CircleShape)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Text(
                            celebrity.name,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Text(
                            "Known For - " + celebrity.knownForDepartment,
                            style = TextStyle(
                                fontSize = 14.sp,
                            ),
                        )
                        Text(
                            "Best known for - " + celebrity.knownForAsStringMax3(),
                            style = TextStyle(
                                fontSize = 14.sp,
                            ),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3,
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCelebListItem(modifier: Modifier = Modifier) {
    TestScrollingAppTheme {
        CelebListItem(
            celebrity = Celebrity(
                false,
                gender = 0,
                id = 3232,
                knownFor = emptyList(),
                name = "Ashish",
                originalName = "Legend",
                popularity = 23.0,
                profilePath = "profile path",
                knownForDepartment = "Singing"
            ),
            onClick = {},
            modifier = modifier
        )
    }
}