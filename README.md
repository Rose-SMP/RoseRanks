# Rose Ranks
### A permissions and ranks mod inspired by PermissionsEX for BTA!

---

## Configuration
#### Serverwide settings for server-by-server customization.
- `defaultGroup`: The group new players are assigned to on first join.
- `chatFormat`: How player chat messages are formatted.

Stored at `/config/roseranks/config.yml`.

---

## Groups
#### Groups (aka ranks/roles) are used for assigning permissions to players, overriding the vanilla op/non-op binary.
### Fields
- `prefix`: The text shown before a player's name in chat to indicate their group.
- `parents`: The group(s) whose permissions a group inherits
- `permissions`: The commands members have access to. (MOD_ID.command)

Each is stored as a `yml` file in `/config/roseranks/groups`.

---

## Users
#### Assigned to groups, and can have individually assigned permissions and prefixes.
### Fields
- `group`: The group the user is a member of.

Each is stored as a `yml` file in `/config/roseranks/users`.

---

## Commands
Most commands are subcommands of `/rr`.

### /rr groups
Prints a list of all groups.
### /rr <group|user\>
Prints the properties of the specified group/user.
### /rr creategroup <name\> [parents]
Creates a new group with the specified name, and optionally with the specified parents.
### /rr <group|user> <grant|revoke\> <permission\>
Grants or revokes <permission\> to the specified group/user.
### /rr <group\> parent <add|remove\> <group\>
Adds or removes a parent group from the specified group.
