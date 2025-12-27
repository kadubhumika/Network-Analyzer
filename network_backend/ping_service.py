import re
import socket
import os,platform
import subprocess


def ping_service(host):
    system = platform.system().lower()

    if 'windows' in system:
        cmd = f"ping -n 1 {host}"
    else:
        cmd = f"ping -c 2 {host}"
    try:
        result = subprocess.run(cmd,capture_output=True, text=True, timeout=1)
        output = result.stdout
        success = result.returncode == 0

        latency = None
        match = re.search(r'time[<=]\s*(\d+)',output)
        if match:
            latency = match.group(1)

        return {
            "host": host,
            "output": output,
            "latency": latency,
            "success": success,
        }
    except Exception as e:
        return {
            "target": host,
            "output":"",
            "latency": None,
            "success": False,
            "error": str(e)

        }








