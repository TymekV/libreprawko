package rocks.agin.libreprawko.ui.pages.nav3

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.ui.graphics.TransformOrigin

val EmphasizedDecelerateEasing =
    CubicBezierEasing(0.2f, 0.85f, 0.7f, 1f)

val EmphasizedAccelerateEasing =
    CubicBezierEasing(0.3f, 0f, 0.8f, 0.15f)

const val TRANSITION_DURATION = 400

fun forwardTransition(): ContentTransform =
    (
        slideInHorizontally(
            animationSpec =
                tween(
                    TRANSITION_DURATION,
                    easing = EmphasizedDecelerateEasing,
                ),
            initialOffsetX = { (it * 0.5f).toInt() },
        ) +
            scaleIn(
                animationSpec =
                    tween(
                        TRANSITION_DURATION,
                        easing = EmphasizedDecelerateEasing,
                    ),
                initialScale = 0.92f,
                transformOrigin = TransformOrigin.Center,
            ) +
            fadeIn(
                animationSpec =
                    tween(
                        TRANSITION_DURATION,
                        easing = EmphasizedAccelerateEasing,
                    ),
            )
    ) togetherWith (
        slideOutHorizontally(
            animationSpec =
                tween(
                    TRANSITION_DURATION,
                    easing = EmphasizedAccelerateEasing,
                ),
            targetOffsetX = { -(it * 0.25f).toInt() },
        ) +
            fadeOut(
                animationSpec =
                    tween(
                        TRANSITION_DURATION / 2,
                        easing = EmphasizedAccelerateEasing,
                    ),
            )
    )

fun popTransition(): ContentTransform =
    (
        slideInHorizontally(
            animationSpec =
                tween(
                    TRANSITION_DURATION,
                    easing = EmphasizedDecelerateEasing,
                ),
            initialOffsetX = { -(it * 0.25f).toInt() },
        ) +
            scaleIn(
                animationSpec =
                    tween(
                        TRANSITION_DURATION,
                        easing = EmphasizedDecelerateEasing,
                    ),
                initialScale = 0.95f,
            ) +
            fadeIn(
                animationSpec =
                    tween(
                        TRANSITION_DURATION / 2,
                        easing = EmphasizedDecelerateEasing,
                    ),
            )
    ) togetherWith (
        slideOutHorizontally(
            animationSpec =
                tween(
                    TRANSITION_DURATION,
                    easing = EmphasizedAccelerateEasing,
                ),
            targetOffsetX = { (it * 0.5f).toInt() },
        ) +
            scaleOut(
                animationSpec =
                    tween(
                        TRANSITION_DURATION,
                        easing = EmphasizedAccelerateEasing,
                    ),
                targetScale = 0.92f,
                transformOrigin = TransformOrigin.Center,
            ) +
            fadeOut(
                animationSpec =
                    tween(
                        TRANSITION_DURATION / 2,
                        easing = EmphasizedAccelerateEasing,
                    ),
            )
    )
