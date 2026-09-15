package dev.kingssack.volt.attachment.drivetrain.pp.mecanum

import com.pedropathing.algorithm.Algorithm
import com.pedropathing.api.PoseFactory
import com.pedropathing.follower.Follower
import com.pedropathing.math.Pose
import com.pedropathing.revhub.drivetrains.Mecanum
import com.pedropathing.revhub.drivetrains.MecanumConfig
import com.pedropathing.revhub.localizers.OctoQuadConfig
import com.pedropathing.revhub.localizers.OctoQuadLocalizer
import com.qualcomm.robotcore.hardware.HardwareMap
import dev.kingssack.volt.attachment.drivetrain.pp.PedroPathingDrivetrain

/**
 * A mecanum [PedroPathingDrivetrain] with an OctoQuad localizer.
 *
 * @param hardwareMap the FTC hardware map
 * @param localizerConfig configuration used by the OctoQuad localizer
 * @param drivetrainConfig configuration specific to the mecanaum drivetrain
 * @param algorithm used by the path follower
 * @param poseFactory the pose factory to use
 * @param initialPose the robot's initial pose
 */
class OctoQuadMecanumPedroPathingDrivetrain(
    hardwareMap: HardwareMap,
    localizerConfig: OctoQuadConfig,
    drivetrainConfig: MecanumConfig,
    algorithm: Algorithm,
    poseFactory: PoseFactory = PoseFactory.radians(),
    initialPose: Pose = poseFactory.of(0.0, 0.0, 0.0),
) :
    PedroPathingDrivetrain(
        Follower(
            OctoQuadLocalizer(hardwareMap, localizerConfig),
            Mecanum(hardwareMap, drivetrainConfig),
            algorithm,
        ),
        poseFactory,
        initialPose,
    )
