package dev.kingssack.volt.attachment.drivetrain.pp.swerve

import com.pedropathing.algorithm.Algorithm
import com.pedropathing.api.PoseFactory
import com.pedropathing.follower.Follower
import com.pedropathing.math.Pose
import com.pedropathing.revhub.drivetrains.Swerve
import com.pedropathing.revhub.drivetrains.SwerveConfig
import com.pedropathing.revhub.localizers.ThreeWheelIMUConfig
import com.pedropathing.revhub.localizers.ThreeWheelIMULocalizer
import com.qualcomm.robotcore.hardware.HardwareMap
import dev.kingssack.volt.attachment.drivetrain.pp.PedroPathingDrivetrain

/**
 * A swerve [PedroPathingDrivetrain] with a three-wheel plus IMU localizer.
 *
 * @param hardwareMap the FTC hardware map
 * @param localizerConfig configuration used by the three-wheel plus IMU localizer
 * @param drivetrainConfig configuration specific to the swerve drivetrain
 * @param algorithm used by the path follower
 * @param poseFactory the pose factory to use
 * @param initialPose the robot's initial pose
 */
class ThreeWheelIMUSwervePedroPathingDrivetrain(
    hardwareMap: HardwareMap,
    localizerConfig: ThreeWheelIMUConfig,
    drivetrainConfig: SwerveConfig,
    algorithm: Algorithm,
    poseFactory: PoseFactory = PoseFactory.radians(),
    initialPose: Pose = poseFactory.of(0.0, 0.0, 0.0),
) :
    PedroPathingDrivetrain(
        Follower(
            ThreeWheelIMULocalizer(hardwareMap, localizerConfig),
            Swerve(hardwareMap, drivetrainConfig),
            algorithm,
        ),
        poseFactory,
        initialPose,
    )
