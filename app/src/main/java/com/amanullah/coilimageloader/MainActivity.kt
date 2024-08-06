package com.amanullah.coilimageloader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Size
import com.amanullah.coilimageloader.ui.theme.CoilImageLoaderTheme
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoilImageLoaderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    innerPadding.calculateBottomPadding()

                    ShowImage()
                }
            }
        }
    }
}

@Composable
private fun ShowImage(imageUrl: String = "https://media.licdn.com/dms/image/D5603AQHadoNvkUMSxg/profile-displayphoto-shrink_200_200/0/1716200445409?e=2147483647&v=beta&t=sK5-UWfX4i0y5TqIE0AP7PVMqFk6xmvHX7CBjF3MXO4") {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current

        val placeholder = R.drawable.ic_launcher_foreground

        // Build an ImageRequest with Coil
        val listener = object : ImageRequest.Listener {

        }
        val imageRequest = ImageRequest.Builder(context)
            .data(imageUrl)
            .listener(listener)
            .dispatcher(Dispatchers.IO)
            .memoryCacheKey(imageUrl)
            .diskCacheKey(imageUrl)
            .placeholder(placeholder)
            .error(placeholder)
            .fallback(placeholder)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()

        // Load and display the image with AsyncImage
        AsyncImage(
            model = imageRequest,
            contentDescription = "Image Description",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}