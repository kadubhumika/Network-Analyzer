import socket
import os
import subprocess


def get_ip():
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        s.connect(("8.8.8.8", 80))
        ip = s.getsockname()[0]
        s.close()
        return ip
    except:
        return socket.gethostbyname(socket.gethostname())

def scan_port(ip, port):
    s = socket.socket()
    s.settimeout(0.5)
    try:
        s.connect((ip, port))
        s.close()
        return True
    except:
        return False



def scan_ports(ip, start=1, end=1024):
    open_ports = []
    for p in range(start, end+1):
        if scan_port(ip, p):
            open_ports.append(p)
    return open_ports


def ping(host):
    return os.system(f"ping -c 1 {host} > /dev/null 2>&1") == 0


def get_mac(ip):
    try:
        output = subprocess.check_output(f"arp -n {ip}", shell=True).decode()
        for line in output.splitlines():
            if ip in line:
                return line.split()[2]
    except:
        return None
    return None

def simple_lan_scan():
    base = ".".join(get_ip().split(".")[:3])
    alive = []

    for i in range(1, 255):
        ip = f"{base}.{i}"
        if ping(ip):
            alive.append(ip)

    return alive




