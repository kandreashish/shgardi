package com.example.testscrollingapp.aboutscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.testscrollingapp.R
import com.example.testscrollingapp.celeb.BASE_IMAGE_URL
import com.example.testscrollingapp.repository.model.CelebDetails
import com.example.testscrollingapp.routes.Routes
import com.example.testscrollingapp.ui.theme.DesertWhite

@Composable
fun AboutScreenRoot(routes: Routes.AboutSection, viewModel: AboutSectionViewmodel, onBack: () -> Unit) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    viewModel.getCelebDetail(routes.personId.toString())
    AboutScreen(state = state, onBack)
}

@Composable
@Preview(showBackground = true)
fun AboutScreen(
    state: AboutCelebScreenState = AboutCelebScreenState(
        isLoading = true, celebrity = CelebDetails(
            adult = false,
            gender = 1,
            id = 32444,
            knownForDepartment = "Singing",
            name = "Ashish",
            popularity = 23.00,
            alsoKnownAs = listOf("AshishTheLegend"),
            biography = "This is Ashish",
            birthday = "14 july 1993",
            deathday = null,
            homepage = "Some homepage",
            imdbId = "4232324",
            placeOfBirth = "India",
            profilePath = "ashish.jpg",
        )
    ),
    onAction: () -> Unit = {}
) {
    val celebrity = state.celebrity

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = DesertWhite,
    ) {

        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = BASE_IMAGE_URL + celebrity?.profilePath,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                placeholder = painterResource(R.drawable.ic_launcher_background)
            )
            celebrity?.let { celebrity ->
                Text(
                    celebrity.name,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    "Known For - " + celebrity.knownForDepartment,
                    style = TextStyle(
                        fontSize = 14.sp,
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    "Biography - " + (celebrity.biography ?: "We don't have a biography for ${celebrity.name}"),
                    style = TextStyle(
                        fontSize = 14.sp,
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Text(
                    "Popularity - " + celebrity.popularity,
                    style = TextStyle(
                        fontSize = 14.sp,
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = ("Gender - " + if (celebrity.gender == 0) "Not set / not specified" else if (celebrity.gender == 1) "Female" else if (celebrity.gender == 2) "Male" else "Non-binary"),
                    style = TextStyle(
                        fontSize = 14.sp,
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

        }
        Box(modifier = Modifier){
            Icon(
                contentDescription = "Back press",
                modifier = Modifier.padding(8.dp).size(30.dp).clickable {
                    onAction()
                },
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
            )
        }
    }
}