package dev.kingssack.volt.attachment.drivetrain.pp

import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.InstantAction
import com.acmerobotics.roadrunner.PoseVelocity2d
import com.pedropathing.api.PoseFactory
import com.pedropathing.follower.Follower
import com.pedropathing.math.Pose
import com.pedropathing.paths.Path
import dev.kingssack.volt.annotations.VoltAction
import dev.kingssack.volt.attachment.drivetrain.Drivetrain
import org.firstinspires.ftc.robotcore.external.Telemetry

/**
 * A PedroPathing [dev.kingssack.volt.attachment.drivetrain.Drivetrain].
 *
 * A pre-built [dev.kingssack.volt.attachment.drivetrain.Drivetrain] that integrates with the
 * PedroPathing library.
 *
 * @param follower the path follower instance
 * @param poseFactory the pose factory to use
 * @param initialPose the robot's initial pose
 * @property pose the robot's current pose
 */
abstract class PedroPathingDrivetrain(
    protected val follower: Follower,
    val poseFactory: PoseFactory = PoseFactory.radians(),
    initialPose: Pose = poseFactory.of(0.0, 0.0, 0.0),
) : Drivetrain() {
    val pose: Pose
        get() = follower.pose()

    init {
        follower.setPose(initialPose)
        follower.update()
    }

    override fun setDrivePowers(powers: PoseVelocity2d) {
        follower.manual(powers.linearVel.x, powers.linearVel.y, powers.angVel)
    }

    /**
     * Follows the given path using PedroPathing.
     *
     * @param path to follow
     * @return the action that follows the provided [path]
     */
    @VoltAction(name = "Follow Path", description = "Follows the given path using PedroPathing")
    fun followPath(path: Path): Action = Action {
        if (follower.atParametricEnd()) follower.follow(path)
        follower.update()
        !follower.atParametricEnd()
    }

    /**
     * Holds the specified [holdPose] using PedroPathing. Completes instantly.
     *
     * @param holdPose the pose to hold, defaults to the current pose
     * @return the action that holds the specified [holdPose]
     */
    @VoltAction(name = "Hold Path", description = "Holds a pose using PedroPathing")
    fun hold(holdPose: Pose = pose): Action = InstantAction {
        follower.hold(holdPose)
    }

    context(telemetry: Telemetry)
    override fun update() {
        follower.update()

        super.update()
        with(telemetry) {
            addData("x", pose.x())
            addData("y", pose.y())
            addData("heading", pose.heading())
        }
    }
}
