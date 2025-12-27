import socket
import platform
import uuid

def get_network_info():
    return {
        "local_ip": socket.gethostbyname(socket.gethostname()),
        "hostname": socket.gethostname(),
        "os": platform.system(),
        "os_version": platform.version(),
        "architecture": platform.architecture()[0],
        "mac_address": ':'.join('%02x' % (uuid.getnode() >> ele & 0xff)
                                for ele in range(0,8*6,8))[::-1]
    }
