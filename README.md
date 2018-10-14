# Duo Workaround AutoApprover
Hey OSU, do you hate the mild inconvenience of Duo two-factor authentication? I know I do so I made a workaround. I'm all for two-factor authentication, but I wanted this so I made it and decided to share.

It's an android app (for versions KitKat and up) that watches notifications for the Duo push notification and then triggers the approve action. For it to work your phone must be on, the service must be running (it should start on boot) and the service must have permission to view notifications. The autoapprover will probably autoapprove ALL Duo notifications, so Carmen, Buckeyelink and anything else, and it probably works on anything else that uses Duo. USE THIS AT YOUR OWN RISK.

Disclaimer: I only spent a few hours on this and I have only tested this on my phone so there is NO GUARANTEE that it will work on all phones at all times. DO NOT RELY ON THIS FOR ANYTHING IMPORTANT - ANY ISSUES THAT ARISE ARE YOUR RESPONSIBILITY. I can probably help debug issues but I'm also busy so you'll likely have to do it yourself. Sometimes Duo won't put out a notification so you have to give it background permissions, and you might have to fiddle with settings for the autoapprover too.

If you don't trust me to not read all your notifications (which you shouldn't), you can view the source here to copy it into an Android Studio project and build it for yourself, or you can just grab the apk if you don't care. The repository contains the build.gradle, AndroidManifest.xml, MainActivity.java, activity_main.xml, Autostart.java and NotificationListener.java files. Feel free to modify it - you can probably set it up to only approve notifications at certain times or from certain computers, but this is left as an exercise to the reader.

Happy Authenticating!
