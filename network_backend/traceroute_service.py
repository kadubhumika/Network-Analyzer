import subprocess
import platform
import re

def traceroute_service(host: str):
    system = platform.system().lower()

    if "windows" in system:
        cmd = ["tracert", "-d", host]
    else:
        cmd = ["traceroute", "-n", host]

    try:
        result = subprocess.run(
            cmd,
            capture_output=True,
            text=True,
            timeout=30
        )

        hops = []
        max_ttl = 0

        for line in result.stdout.splitlines():
            line = line.strip()
            if not line or not line[0].isdigit():
                continue

            parts = line.split()
            hop_no = int(parts[0])
            max_ttl = hop_no

            ip_match = re.search(r"(\d+\.\d+\.\d+\.\d+)", line)
            latency_match = re.search(r"(\d+)\s*ms", line)

            hops.append({
                "hop": hop_no,
                "ip": ip_match.group(1) if ip_match else "*",
                "latency": int(latency_match.group(1)) if latency_match else None,
                "ttl": hop_no,
                "success": ip_match is not None
            })

        return {
            "target": host,
            "output": result.stdout,
            "hops": hops,
            "ttl": max_ttl,
            "bytePackets": 60,
            "success": True,
            "error": None
        }

    except Exception as e:
        return {
            "target": host,
            "output": "",
            "hops": [],
            "ttl": 0,
            "bytePackets": 0,
            "success": False,
            "error": str(e)
        }
