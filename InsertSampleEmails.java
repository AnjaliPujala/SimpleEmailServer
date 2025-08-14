public class InsertSampleEmails {
    public static void main(String[] args) {
        Database db = new Database();

        // Sample emails
        String[][] emails = {
                { "anjali@gmail.com", "venky@gmail.com", "Sprint Planning",
                        "Hi Venky, let's schedule the sprint planning meeting for Monday." },
                { "venky@gmail.com", "anjali@gmail.com", "Re: Sprint Planning",
                        "Thanks Anjali, Monday works for me. What time are we planning?" },
                { "nidhi@gmail.com", "john@gmail.com", "Follow-up: Client Call",
                        "Hi John, following up on our client call yesterday. Please review notes." },
                { "john@gmail.com", "nidhi@gmail.com", "Re: Follow-up: Client Call",
                        "Thanks Nidhi, I will review and update the action items." },
                { "anjali@gmail.com", "nidhi@gmail.com", "New Feature Proposal",
                        "Hi Nidhi, I have drafted a new feature proposal. Kindly review." },
                { "nidhi@gmail.com", "anjali@gmail.com", "Re: New Feature Proposal",
                        "Thanks Anjali, I will go through it and share feedback." },
                { "venky@gmail.com", "john@gmail.com", "Server Downtime",
                        "Hi John, the server will be down for maintenance tonight from 11 PM to 1 AM." },
                { "john@gmail.com", "venky@gmail.com", "Re: Server Downtime",
                        "Thanks Venky, noted. I will inform the team." },
                { "anjali@gmail.com", "john@gmail.com", "Weekly Summary",
                        "Hi John, here is the weekly summary of tasks completed by the team." },
                { "john@gmail.com", "anjali@gmail.com", "Re: Weekly Summary",
                        "Thanks Anjali, looks good. Please share with management." },
                { "nidhi@gmail.com", "venky@gmail.com", "Client Feedback",
                        "Hi Venky, the client has provided feedback on the last deliverable." },
                { "venky@gmail.com", "nidhi@gmail.com", "Re: Client Feedback",
                        "Thanks Nidhi, I will incorporate their feedback." },
                { "anjali@gmail.com", "venky@gmail.com", "Code Review",
                        "Hi Venky, please review the latest code pushed to GitHub." },
                { "venky@gmail.com", "anjali@gmail.com", "Re: Code Review",
                        "Thanks Anjali, I will review and comment." },
                { "john@gmail.com", "anjali@gmail.com", "Task Assignment",
                        "Hi Anjali, please take ownership of the UI updates." },
                { "anjali@gmail.com", "john@gmail.com", "Re: Task Assignment", "Sure John, I will start today." },
                { "nidhi@gmail.com", "anjali@gmail.com", "Bug Report",
                        "Hi Anjali, I found a bug in the latest build. Kindly check." },
                { "anjali@gmail.com", "nidhi@gmail.com", "Re: Bug Report", "Thanks Nidhi, I will fix it today." },
                { "venky@gmail.com", "nidhi@gmail.com", "Meeting Schedule",
                        "Hi Nidhi, let's meet tomorrow at 3 PM to discuss progress." },
                { "nidhi@gmail.com", "venky@gmail.com", "Re: Meeting Schedule", "Confirmed Venky, see you at 3 PM." },
                { "anjali@gmail.com", "john@gmail.com", "Client Onboarding",
                        "Hi John, client onboarding session is scheduled for next Thursday." },
                { "john@gmail.com", "anjali@gmail.com", "Re: Client Onboarding",
                        "Thanks Anjali, I will join the session." },
                { "venky@gmail.com", "anjali@gmail.com", "Database Migration",
                        "Hi Anjali, database migration is scheduled for Sunday." },
                { "anjali@gmail.com", "venky@gmail.com", "Re: Database Migration",
                        "Thanks Venky, I will backup data beforehand." },
                { "nidhi@gmail.com", "john@gmail.com", "Presentation Draft",
                        "Hi John, I have prepared the draft slides for the client presentation." },
                { "john@gmail.com", "nidhi@gmail.com", "Re: Presentation Draft",
                        "Thanks Nidhi, will review and provide feedback." },
                { "anjali@gmail.com", "nidhi@gmail.com", "Release Notes",
                        "Hi Nidhi, attached are the release notes for version 2.1." },
                { "nidhi@gmail.com", "anjali@gmail.com", "Re: Release Notes",
                        "Thanks Anjali, I will circulate to the team." },
                { "john@gmail.com", "venky@gmail.com", "System Upgrade",
                        "Hi Venky, the system upgrade will happen on Saturday at 6 PM." },
                { "venky@gmail.com", "john@gmail.com", "Re: System Upgrade", "Thanks John, I will notify the users." },
                { "anjali@gmail.com", "venky@gmail.com", "Performance Report",
                        "Hi Venky, please find attached the performance report of last month." },
                { "venky@gmail.com", "anjali@gmail.com", "Re: Performance Report", "Thanks Anjali, looks good." },
                { "nidhi@gmail.com", "anjali@gmail.com", "Documentation Update",
                        "Hi Anjali, the documentation for the new module is ready for review." },
                { "anjali@gmail.com", "nidhi@gmail.com", "Re: Documentation Update",
                        "Thanks Nidhi, I will review today." },
                { "john@gmail.com", "anjali@gmail.com", "Team Expansion",
                        "Hi Anjali, we are adding two new members to the team next week." },
                { "anjali@gmail.com", "john@gmail.com", "Re: Team Expansion",
                        "Great John, will welcome them and onboard accordingly." },
                { "venky@gmail.com", "nidhi@gmail.com", "Code Merge",
                        "Hi Nidhi, the latest feature branch is ready to merge." },
                { "nidhi@gmail.com", "venky@gmail.com", "Re: Code Merge", "Thanks Venky, I will merge after review." },
                { "anjali@gmail.com", "john@gmail.com", "Training Session",
                        "Hi John, I have scheduled a training session for the new tools." },
                { "john@gmail.com", "anjali@gmail.com", "Re: Training Session", "Thanks Anjali, I will attend." },
                { "nidhi@gmail.com", "john@gmail.com", "Contract Renewal",
                        "Hi John, the client contract needs to be renewed next month." },
                { "john@gmail.com", "nidhi@gmail.com", "Re: Contract Renewal",
                        "Thanks Nidhi, I will initiate the process." },
                { "anjali@gmail.com", "venky@gmail.com", "Design Feedback",
                        "Hi Venky, please review the latest UI design and share your feedback." },
                { "venky@gmail.com", "anjali@gmail.com", "Re: Design Feedback",
                        "Thanks Anjali, I will provide comments soon." },
                { "john@gmail.com", "anjali@gmail.com", "Server Logs",
                        "Hi Anjali, please check the server logs for the last deployment." },
                { "anjali@gmail.com", "john@gmail.com", "Re: Server Logs", "Thanks John, I will review and report." },
                { "nidhi@gmail.com", "venky@gmail.com", "Testing Schedule",
                        "Hi Venky, testing for the new module is planned for Thursday." },
                { "venky@gmail.com", "nidhi@gmail.com", "Re: Testing Schedule", "Thanks Nidhi, noted." },
                { "anjali@gmail.com", "nidhi@gmail.com", "Meeting Agenda",
                        "Hi Nidhi, here is the agenda for tomorrow's meeting." },
                { "nidhi@gmail.com", "anjali@gmail.com", "Re: Meeting Agenda", "Thanks Anjali, will review." },
                { "john@gmail.com", "venky@gmail.com", "Deployment Plan",
                        "Hi Venky, attached is the deployment plan for next week." },
                { "venky@gmail.com", "john@gmail.com", "Re: Deployment Plan", "Thanks John, will follow this plan." },
                { "anjali@gmail.com", "john@gmail.com", "Client Follow-up",
                        "Hi John, following up with the client regarding last discussion." },
                { "john@gmail.com", "anjali@gmail.com", "Re: Client Follow-up",
                        "Thanks Anjali, will get back to the client." },
                { "nidhi@gmail.com", "anjali@gmail.com", "Bug Fix Confirmation",
                        "Hi Anjali, has the reported bug been fixed?" }
        };

        // Insert emails into sent and inbox tables
        for (String[] email : emails) {
            String sender = email[0];
            String receiver = email[1];
            String subject = email[2];
            String message = email[3];

            db.sendEmail(sender, receiver, subject, message); // Insert into sent
            db.receiveEmail(sender, receiver, subject, message); // Insert into inbox
        }

        db.close();
        System.out.println("All sample emails inserted successfully!");
    }
}
