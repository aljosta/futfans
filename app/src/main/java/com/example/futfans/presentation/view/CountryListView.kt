package com.example.futfans.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.futfans.R
import com.example.futfans.presentation.model.CountryModel
import com.example.futfans.presentation.model.getCountryList

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { AppBar() },
        content = { padding ->
            MainContent(modifier = Modifier.padding(padding))
        }
    )
}

@Composable
fun AppBar() {
    TopAppBar(
        title = { Text(text = "FutFans") },
        navigationIcon = {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "")
        }
    )
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier
) {
    var keyword by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = keyword,
            onValueChange = {
                keyword = it
            },
            placeholder = { Text(text = "Search...") },
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn {
            items(getCountryList()) { country ->
                CountryCard(country)
            }
        }
    }
}

@Composable
fun CountryCard(country: CountryModel) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(20.dp),
        elevation = 2.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(country.image)
                        .placeholder(R.drawable.ic_launcher_background)
                        .transformations(RoundedCornersTransformation(200f))
                        .decoderFactory { result, options, _ ->
                            SvgDecoder(result.source, options)
                        }
                        .build()
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = country.name,
                style = MaterialTheme.typography.body1.copy(fontSize = 20.sp),
                modifier = Modifier.padding(start = 50.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    MainScreen()
}
