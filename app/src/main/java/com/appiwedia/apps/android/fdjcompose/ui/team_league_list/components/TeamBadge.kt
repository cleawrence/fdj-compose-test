package com.appiwedia.apps.android.fdjcompose.ui.team_league_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun TeamBadge(
    teamBannerUrl: String,
    modifier: Modifier = Modifier,
    teamName: String,
    onclickBadge: (String) -> Unit
) {
    AsyncImage(
        modifier = modifier
            .clickable { onclickBadge.invoke(teamName) }
            .wrapContentHeight()
            .wrapContentWidth(),
        contentScale = ContentScale.FillBounds,
        model = ImageRequest.Builder(LocalContext.current).crossfade(true).data(teamBannerUrl)
            .build(),
        contentDescription = "TeamBanner",
    )
}